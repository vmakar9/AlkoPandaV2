package com.alibou.security.comment;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {
    private CommentRepository commentRepository;

    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> getCommentsByRestaurantId(int restaurantId){
        return commentRepository.findByRestaurantIdAndParentIdIsNull(restaurantId);
    }

    public List<Comment> getReplies(int commentId){
        return commentRepository.findByParentId(commentId);
    }

    public void deleteComment(int commentId){
        commentRepository.deleteById(commentId);
    }

    public Comment updateComment(int commentId,String text){
        Comment comment = commentRepository.findById(commentId).orElse(null);
        if(comment !=  null){
            comment.setText(text);
            return commentRepository.save(comment);
        }
        return null;
    }

    public Comment getCommentById(int commentId){
        return commentRepository.findById(commentId).get();
    }
}
