package org.zerobase.publicwifiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerobase.publicwifiservice.dto.PublicWifiDto;
import org.zerobase.publicwifiservice.dto.request.UserLocationRequest;
import org.zerobase.publicwifiservice.dto.response.PublicWifiResponse;
import org.zerobase.publicwifiservice.dto.response.Response;
import org.zerobase.publicwifiservice.service.PublicWifiLogService;
import org.zerobase.publicwifiservice.service.PublicWifiService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/public_wifi")
@RequiredArgsConstructor
public class PublicWifiController {
    private final PublicWifiService publicWifiService;
    private final PublicWifiLogService publicWifiLogService;

    @ResponseBody
    @GetMapping("/apiUpdateAll")
    public Response<Void> updateAllByApi() {
        try {
            publicWifiService.updatePublicWifiAll();
            return Response.success();
        } catch (RuntimeException e) {
            log.error("업데이트 실패", e);
            return Response.fail();
        }
    }

    @PostMapping("/findNearestWifi")
    public String findNearestWifi(
            @ModelAttribute @Validated UserLocationRequest userLocationRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            log.warn("formBindingResult={}", bindingResult);
            return "redirect:/";
        }
        List<PublicWifiDto> nearestWifis = publicWifiService.getNearestWifis(userLocationRequest.getLatitude(), userLocationRequest.getLongitude());
        redirectAttributes.addFlashAttribute(
                "wifiList",
                nearestWifis.stream().map(PublicWifiResponse::fromDto).toList()
        );
        publicWifiLogService.saveWifiLog(
                userLocationRequest.getLatitude(),
                userLocationRequest.getLongitude()
        );

        return "redirect:/";
    }

    @GetMapping("/detail/{id}")
    public String detailPublicWIfi(
            HttpServletResponse response,
            @PathVariable Long id,
            @RequestParam Double distance,
            Model model
    ) {
        response.setContentType("text/html; charset=utf-8");
        model.addAttribute(
                "publicWifi",
                PublicWifiResponse.fromDto(publicWifiService.getWifi(id, distance))
        );

        return "/public_wifi/detail";
    }
}
