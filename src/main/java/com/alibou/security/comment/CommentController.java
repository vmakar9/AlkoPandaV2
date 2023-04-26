package com.alibou.security.comment;

import com.alibou.security.restaurant.Restaurant;
import com.alibou.security.restaurant.RestaurantRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class CommentController {
    private CommentService commentService;
    private RestaurantRepository restaurantRepository;

    @PostMapping("/restaurant/{restaurantId}/comments")
    public ResponseEntity<Comment> addComment(@PathVariable int restaurantId, @RequestBody Comment comment){
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
        if(restaurant !=  null){
            comment.setRestaurant(restaurant);
            Comment newComment = commentService.addComment(comment);
            return ResponseEntity.ok(newComment);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("restaurant/{restaurantId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByRestaurantsId(@PathVariable int restaurantId){
        List<Comment> comments = commentService.getCommentsByRestaurantId(restaurantId);
        return ResponseEntity.ok(comments);
    }

    @GetMapping("/comments/{commentId}/replies")
    public ResponseEntity<List<Comment>> getReplies(@PathVariable int commentId){
        List<Comment> replies = commentService.getReplies(commentId);
        return ResponseEntity.ok(replies);
    }

    @PostMapping("/comments/{commentId}/replies")
    public ResponseEntity<Comment> addReply(@PathVariable int commentId,@RequestBody Comment comment){
        Comment parentComment = commentService.getCommentById(commentId);
        if(parentComment !=  null){
            comment.setRestaurant(parentComment.getRestaurant());
            comment.setParentId(parentComment.getParentId());
            Comment newComment = commentService.addComment(comment);
            return ResponseEntity.ok(newComment);
        }else {
            return  ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable int commentId){
        Comment comment = commentService.getCommentById(commentId);
        if(comment !=  null){
            commentService.deleteComment(commentId);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping ("/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable int commentId,@RequestBody Comment newComment){
        Comment updatedComment = commentService.updateComment(commentId, newComment.getText());
        if(updatedComment !=  null){
            return ResponseEntity.ok(updatedComment);
        }
        return ResponseEntity.notFound().build();
    }
}
