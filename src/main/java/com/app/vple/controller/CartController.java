package com.app.vple.controller;

import com.app.vple.domain.User;
import com.app.vple.domain.dto.CartAddDto;
import com.app.vple.domain.dto.CartDetailDto;
import com.app.vple.domain.dto.MyCartDto;
import com.app.vple.service.CartService;
import com.app.vple.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth/cart")
public class CartController {

    private final UserService userService;

    private final CartService cartService;

    @GetMapping
    public ResponseEntity<?> cartList(HttpServletRequest request) {
        try {
            User loginUser = userService.getUser(request);
            List<MyCartDto> cart = cartService.findCart(loginUser.getEmail());
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> cartDetails(@PathVariable Long id) {
        try {
            CartDetailDto cart = cartService.findCartDetails(id);
            return new ResponseEntity<>(cart, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> cartAdd(HttpServletRequest request, @Validated @RequestBody CartAddDto cartAddDto) {
        try {
            User loginUser = userService.getUser(request);
            String name = cartService.addCart(cartAddDto, loginUser.getEmail());
            return new ResponseEntity<>(name + " 등록완료", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cartRemove(@PathVariable Long id) {
        try {
            String name = cartService.removeCart(id);
            return new ResponseEntity<>(name + "을 삭제했습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
