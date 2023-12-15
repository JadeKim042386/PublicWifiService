package org.zerobase.publicwifiservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerobase.publicwifiservice.dto.request.BookmarkGroupRequest;
import org.zerobase.publicwifiservice.dto.response.BookmarkGroupResponse;
import org.zerobase.publicwifiservice.dto.response.Response;
import org.zerobase.publicwifiservice.exception.BookmarkGroupException;
import org.zerobase.publicwifiservice.exception.ErrorCode;
import org.zerobase.publicwifiservice.service.BookmarkGroupService;
import org.zerobase.publicwifiservice.service.PaginationService;

import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
@RequestMapping("/bookmark_group")
@RequiredArgsConstructor
public class BookmarkGroupController {
    private final BookmarkGroupService bookmarkGroupService;
    private final PaginationService paginationService;

    @GetMapping
    public String getBookmarkGroups(
            HttpServletResponse response,
            @PageableDefault(size = 25, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
            Model model
    ) {
        response.setContentType("text/html; charset=utf-8");
        Page<BookmarkGroupResponse> groups = bookmarkGroupService.getBookmarkGroups(pageable).map(BookmarkGroupResponse::fromDto);
        model.addAttribute(
                "groups",
                groups
        );
        model.addAttribute(
                "paginationBarNumbers",
                paginationService.getPaginationBarNumbers(
                        pageable.getPageNumber(),
                        groups.getTotalPages()
                )
        );

        return "/bookmark/bookmark_group";
    }

    @PostMapping("/save")
    public String saveBookmarkGroup(
            @ModelAttribute @Validated BookmarkGroupRequest bookmarkGroupRequest,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            log.warn("formBindingResult={}", bindingResult);
            redirectAttributes.addFlashAttribute("errorMessage", bindingResult.getAllErrors());
            return "redirect:/bookmark_group";
        }
        try {
            bookmarkGroupService.saveBookmarkGroup(bookmarkGroupRequest.toDto());
            return "redirect:/bookmark_group";
        } catch (BookmarkGroupException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/bookmark_group";
        }
    }

    @ResponseBody
    @GetMapping("/update")
    public Response<BookmarkGroupResponse> updateBookmarkGroup(
            @ModelAttribute @Validated BookmarkGroupRequest bookmarkGroupRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            log.warn("formBindingResult={}", bindingResult);
            throw new BookmarkGroupException(ErrorCode.BOOKMARK_GROUP_INVALID);
        }

        try {
            return Response.success(
                    BookmarkGroupResponse.fromDto(
                            bookmarkGroupService.updateBookmarkGroup(bookmarkGroupRequest.toDto())
                    )
            );
        } catch (BookmarkGroupException e) {
            log.error(e.getMessage());
            throw e;
        }
    }

    @ResponseBody
    @GetMapping("/delete/{id}")
    public Response<Void> deleteBookmarkGroup(@PathVariable Long id) {
        try {
            bookmarkGroupService.deleteBookmarkGroup(id);
            return Response.success();
        } catch (BookmarkGroupException e) {
            log.error(e.getMessage());
            throw e;
        }
    }
}
