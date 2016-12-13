package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.usc.rai.coego.martin.demiurgo.json.CheckRoomResponse;
import es.usc.rai.coego.martin.demiurgo.json.ExecuteCodeRequest;
import es.usc.rai.coego.martin.demiurgo.json.ExecuteCodeResponse;
import es.usc.rai.coego.martin.demiurgo.json.JsonAction;
import es.usc.rai.coego.martin.demiurgo.json.NarrateActionRequest;
import es.usc.rai.coego.martin.demiurgo.json.NarrateActionResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.NarrateActionForm;
import es.usc.rai.coego.martin.demiurgo.web.forms.ProcessCodeForm;

@Controller
public class RoomController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@GetMapping("/room")
	public String getNewActionForm(@RequestParam("path") String path, ProcessCodeForm processCodeForm, Model model,
			RedirectAttributes ra) {
		CheckRoomResponse res = requestRoomData(path);
		model.addAttribute("room", res.getRoom());
		if (res.getUnpublishedAction() != null) {
			// There is an unpublished action
			ra.addFlashAttribute("action", res.getUnpublishedAction());
			return "redirect:/narrate?id="+res.getUnpublishedAction().getId();
		}
		// There are no actions
		processCodeForm.setPath(path);
		return "room";
	}

	@PostMapping(path = "/room")
	public String executeCode(@Valid ProcessCodeForm processCodeForm, BindingResult br, Model m, RedirectAttributes ra) {
		
		if (br.hasErrors()) {
			m.addAttribute("room", requestRoomData(processCodeForm.getPath()).getRoom());
			return "room";
		}

		ExecuteCodeRequest req = new ExecuteCodeRequest();
		req.setPath(processCodeForm.getPath());
		req.setCode(processCodeForm.getCode());
		ExecuteCodeResponse res = dc.doPost(user.getToken(), "executecode", req, ExecuteCodeRequest.class,
				ExecuteCodeResponse.class);

		if (res.getStatus().isOk()) {
			ra.addFlashAttribute("action", res.getAction());
			return "redirect:/narrate?id=" + res.getAction().getId();
		} else {
			// There was errors in code
			m.addAttribute("parseErrors", res.getStatus().getDescription());
			m.addAttribute("room", requestRoomData(processCodeForm.getPath()).getRoom());
			return "room";
		}

	}
	
	@GetMapping("/narrate")
	public String getNarrationForm(@RequestParam("id") String id, NarrateActionForm narrateActionForm, Model model) {
		if (!model.containsAttribute("action")) { // Flash attribute
			LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
			params.add("id", id);
			JsonAction a = dc.doGet(user.getToken(), "action", params, JsonAction.class);
			model.addAttribute("action", a);
			narrateActionForm.setNarration(a.getNarration());
		}
		narrateActionForm.setActionId(Long.valueOf(id));
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

		return "redirect:/history?path="+res.getAction().getRoom();
	}

	private CheckRoomResponse requestRoomData(String path) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("path", path);
		return dc.doGet(user.getToken(), "checkroom", params, CheckRoomResponse.class);
	}
}
