package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.usc.rai.coego.martin.demiurgo.json.AllRoomPathsResponse;
import es.usc.rai.coego.martin.demiurgo.json.CheckRoomResponse;
import es.usc.rai.coego.martin.demiurgo.json.CreateRoomRequest;
import es.usc.rai.coego.martin.demiurgo.json.DeleteVariableRequest;
import es.usc.rai.coego.martin.demiurgo.json.DeleteVariableResponse;
import es.usc.rai.coego.martin.demiurgo.json.DestroyObjectRequest;
import es.usc.rai.coego.martin.demiurgo.json.DestroyObjectResponse;
import es.usc.rai.coego.martin.demiurgo.json.DestroyRoomRequest;
import es.usc.rai.coego.martin.demiurgo.json.DestroyRoomResponse;
import es.usc.rai.coego.martin.demiurgo.json.ExecuteCodeRequest;
import es.usc.rai.coego.martin.demiurgo.json.ExecuteCodeResponse;
import es.usc.rai.coego.martin.demiurgo.json.JsonAction;
import es.usc.rai.coego.martin.demiurgo.json.JsonRoom;
import es.usc.rai.coego.martin.demiurgo.json.NarrateActionRequest;
import es.usc.rai.coego.martin.demiurgo.json.NarrateActionResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.beans.VarTypeToCss;
import es.usc.rai.coego.martin.demiurgo.web.forms.CreateRoomForm;
import es.usc.rai.coego.martin.demiurgo.web.forms.NarrateActionForm;
import es.usc.rai.coego.martin.demiurgo.web.forms.ProcessCodeForm;

@Controller
public class RoomController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;
	
	@Autowired
	VarTypeToCss typecss;

	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}
	
	@ModelAttribute("typecss")
	public VarTypeToCss getTypeToCss() {
		return typecss;
	}

	@GetMapping("/rooms")
	public String seeAllRooms(CreateRoomForm createRoomForm, Model model) {
		AllRoomPathsResponse res = dc.doGet(user.getToken(), "roompaths", AllRoomPathsResponse.class);
		model.addAttribute("paths", res.getPaths());
		return "seeallrooms";
	}

	@PostMapping("createroom")
	public String createRoom(@Valid CreateRoomForm createRoomForm) {
		CreateRoomRequest req = new CreateRoomRequest();
		req.setPath(createRoomForm.getPath());
		JsonRoom r = dc.doPost(user.getToken(), "createroom", req, CreateRoomRequest.class, JsonRoom.class);
		return "redirect:/room?path=" + r.getLongPath();
	}

	@GetMapping("/room")
	public String getNewActionForm(@RequestParam("path") String path, ProcessCodeForm processCodeForm, Model model,
			RedirectAttributes ra) {
		CheckRoomResponse res = requestRoomData(path);
		model.addAttribute("room", res.getRoom());
		if (res.getUnpublishedAction() != null) {
			// There is an unpublished action
			ra.addFlashAttribute("action", res.getUnpublishedAction());
			return "redirect:/narrate?id=" + res.getUnpublishedAction().getId();
		}
		// There are no actions
		AllRoomPathsResponse res2 = dc.doGet(user.getToken(), "roompaths", AllRoomPathsResponse.class);
		model.addAttribute("paths", res2.getPaths());
		processCodeForm.setPath(path);
		return "room";
	}

	@PostMapping(path = "/room")
	public String executeCode(@ModelAttribute("processCodeForm") @Valid ProcessCodeForm processCodeForm, BindingResult br, Model model,
			RedirectAttributes ra) {

		if (br.hasErrors()) {
			model.addAttribute("room", requestRoomData(processCodeForm.getPath()).getRoom());
			AllRoomPathsResponse res2 = dc.doGet(user.getToken(), "roompaths", AllRoomPathsResponse.class);
			model.addAttribute("paths", res2.getPaths());
			return "room";
		}

		ExecuteCodeRequest req = new ExecuteCodeRequest();
		req.setPath(processCodeForm.getPath());
		req.setCode(processCodeForm.getCode());
		req.setCreateAction(processCodeForm.isCreateAction());
		ExecuteCodeResponse res = dc.doPost(user.getToken(), "executecode", req, ExecuteCodeRequest.class,
				ExecuteCodeResponse.class);

		if (res.getStatus().isOk()) {
			if (req.isCreateAction()) {
				return "redirect:/narrate?id=" + res.getAction().getId();
			} else {
				return "redirect:/room?path=" + req.getPath();
			}
		} else {
			// There was errors in code
			ra.addFlashAttribute("parseErrors", res.getStatus().getDescription());
			ra.addFlashAttribute("processCodeForm", processCodeForm);
			// ra.addAttribute("room",
			// requestRoomData(processCodeForm.getPath()).getRoom());
			return "redirect:/room?path=" + processCodeForm.getPath();
		}

	}

	@GetMapping("/narrate")
	public String getNarrationForm(@RequestParam("id") String id, NarrateActionForm narrateActionForm, Model model) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("id", id);
		JsonAction a = dc.doGet(user.getToken(), "action", params, JsonAction.class);
		model.addAttribute("action", a);

		model.addAttribute("room", requestRoomData(a.getRoom()).getRoom());

		narrateActionForm.setNarration(a.getNarration());
		narrateActionForm.setActionId(Long.parseLong(id));
		return "narrate";
	}

	@PostMapping("/narrate")
	public String narrateAction(@Valid NarrateActionForm narrateActionForm, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("id", Long.toString(narrateActionForm.getActionId()));
			JsonAction a = dc.doGet(user.getToken(), "action", params, JsonAction.class);
			model.addAttribute("action", a);
			return "narrate";
		}

		NarrateActionRequest req = new NarrateActionRequest();
		req.setId(narrateActionForm.getActionId());
		req.setNarration(narrateActionForm.getNarration());
		NarrateActionResponse res = dc.doPost(user.getToken(), "narrateaction", req, NarrateActionRequest.class,
				NarrateActionResponse.class);

		return "redirect:/history?path=" + res.getAction().getRoom() + "#act" + res.getAction().getId();
	}

	@GetMapping("delvar")
	public String deleteVariable(@RequestParam("path") String path, @RequestParam("var") String var) {
		DeleteVariableRequest req = new DeleteVariableRequest();
		req.setPath(path);
		req.setVarName(var);
		dc.doPost(user.getToken(), "delvar", req, DeleteVariableRequest.class, DeleteVariableResponse.class);
		return "redirect:/room?path=" + path;
	}

	@GetMapping("destroyobj")
	public String destroyObject(@RequestParam("id") long id, @RequestParam("back") String back) {
		DestroyObjectRequest req = new DestroyObjectRequest();
		req.setObjId(id);
		req.setDestroyContents(true);
		dc.doPost(user.getToken(), "destroyobj", req, DestroyObjectRequest.class, DestroyObjectResponse.class);
		return "redirect:/" + back;
	}

	@GetMapping("destroyroom")
	public String destroyRoom(@RequestParam("path") String path) {
		DestroyRoomRequest req = new DestroyRoomRequest();
		req.setPath(path);
		dc.doPost(user.getToken(), "destroyroom", req, DestroyRoomRequest.class, DestroyRoomResponse.class);
		return "redirect:/rooms";
	}

	private CheckRoomResponse requestRoomData(String path) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("path", path);
		return dc.doGet(user.getToken(), "checkroom", params, CheckRoomResponse.class);
	}
}
