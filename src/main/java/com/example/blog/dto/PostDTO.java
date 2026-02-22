package com.example.blog.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record PostDTO(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String category,
        @NotNull ArrayList<String> tags) {
}
