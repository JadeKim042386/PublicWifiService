package org.zerobase.publicwifiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerobase.publicwifiservice.dto.request.BookmarkGroupRequest;
import org.zerobase.publicwifiservice.dto.response.BookmarkGroupResponse;
import org.zerobase.publicwifiservice.service.BookmarkGroupService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/bookmark_group")
@RequiredArgsConstructor
public class BookmarkGroupController {
    private final BookmarkGroupService bookmarkGroupService;

    @GetMapping
    public String getBookmarkGroups(
            HttpServletResponse response,
            @PageableDefault(size = 25, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        response.setContentType("text/html; charset=utf-8");
        model.addAttribute(
                "groups",
                bookmarkGroupService.getBookmarkGroups(pageable).map(BookmarkGroupResponse::fromDto)
        );

        return "/bookmark/bookmark_group";
    }

    @PostMapping("/save")
    public String saveBookmarkGroup(
            @ModelAttribute BookmarkGroupRequest bookmarkGroupRequest
    ) {
        bookmarkGroupService.saveBookmarkGroup(bookmarkGroupRequest.toDto());

        return "redirect:/bookmark_group";
    }
}
