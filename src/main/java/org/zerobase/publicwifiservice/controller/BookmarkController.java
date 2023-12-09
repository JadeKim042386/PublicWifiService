package org.zerobase.publicwifiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zerobase.publicwifiservice.dto.request.BookmarkGroupRequest;
import org.zerobase.publicwifiservice.dto.request.BookmarkRequest;
import org.zerobase.publicwifiservice.dto.response.BookmarkGroupResponse;
import org.zerobase.publicwifiservice.dto.response.BookmarkResponse;
import org.zerobase.publicwifiservice.dto.response.Response;
import org.zerobase.publicwifiservice.service.BookmarkGroupService;
import org.zerobase.publicwifiservice.service.BookmarkService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/bookmark")
@RequiredArgsConstructor
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public String getBookmarks(
            HttpServletResponse response,
            @PageableDefault(size = 25, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        response.setContentType("text/html; charset=utf-8");
        model.addAttribute(
                "bookmarks",
                bookmarkService.getBookmarks(pageable).map(BookmarkResponse::fromDto)
        );

        return "/bookmark/bookmark";
    }

    @ResponseBody
    @GetMapping("/save")
    public Response<Void> saveBookmark(
            @ModelAttribute @Validated BookmarkRequest bookmarkRequest
    ) {
        try {
            bookmarkService.saveBookmark(bookmarkRequest.getGroupId(), bookmarkRequest.getWifiId());
            return Response.success();
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기 저장 실패", e);
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/delete/{id}")
    public Response<Void> deleteBookmarkGroup(@PathVariable Long id) {
        try {
            bookmarkService.deleteBookmark(id);
            return Response.success();
        } catch (IllegalArgumentException e) {
            log.error("즐겨찾기그룹 삭제 실패", e);
            throw e;
        }
    }
}
