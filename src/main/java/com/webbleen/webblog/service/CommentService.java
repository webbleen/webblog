package com.webbleen.webblog.service;

import com.webbleen.webblog.entity.Comment;

import java.util.List;

/**
 * @author ：webbleen
 * @date ：Created in 2020-06-14 14:02
 * @description：
 */

public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
