package com.epicgameshub.epic_games_hub.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BorrowedGameResponse {
    private Integer id;
    private String title;
    private String publisher;
    private double rate;
    private boolean returned;
    private boolean returnApproved;
}
