package org.zerobase.publicwifiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerobase.publicwifiservice.dto.response.PublicWifiLogResponse;
import org.zerobase.publicwifiservice.dto.response.Response;
import org.zerobase.publicwifiservice.service.PublicWifiLogService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/wifi_log")
@RequiredArgsConstructor
public class PublicWifiLogController {
    private final PublicWifiLogService publicWifiLogService;

    @GetMapping
    public String getWifiLogs(
            HttpServletResponse response,
            @PageableDefault(size = 25, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable,
            Model model
    ) {
        response.setContentType("text/html; charset=utf-8");
        model.addAttribute(
                "logs",
                publicWifiLogService.getWifiLogs(pageable).map(PublicWifiLogResponse::fromDto)
        );

        return "/logs/wifi_log";
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response<Void> deleteWifiLog(@PathVariable Long id) {
        try {
            publicWifiLogService.deleteWifiLog(id);
            return Response.success();
        } catch (IllegalArgumentException e) {
            log.error("Wifi Log 삭제 실패", e);
            return Response.fail();
        }
    }
}
