import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TennisMatchTest {

    enum P{A,B}

    private TennisMatch match;
    @Before
    public void setUp() {
        match = new TennisMatch();
    }

    @Test
    public void shouldDisplayInitialGameScore(){
        assertEquals("0:0", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_15_0_ifPlayerAScores(){
        playerScores(P.A, 1);
        assertEquals("15:0", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_30_0_ifPlayerAScores2Times(){
        playerScores(P.A, 2);
        assertEquals("30:0", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_40_0_ifPlayerAScores3Times(){
        playerScores(P.A, 3);
        assertEquals("40:0", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_0_15_ifPlayerBScores(){
        playerScores(P.B, 1);
        assertEquals("0:15", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_0_30_ifPlayerBScores2Times(){
        playerScores(P.B, 2);
        assertEquals("0:30", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_0_40_ifPlayerBScores3Times(){
        playerScores(P.B, 3);
        assertEquals("0:40", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_duece_ifPlayersScore3TimesEach(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        assertEquals("deuce", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_pA_Adv_ifPlayerAHasAdvantage(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        playerScores(P.A, 1);
        assertEquals("pA_Adv", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_pB_Adv_ifPlayerBHasAdvantage(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        match.playerBScores();
        assertEquals("pB_Adv", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_duece_ifALooseAdvantage(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        playerScores(P.A, 1);
        playerScores(P.B, 1);
        assertEquals("deuce", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_duece_ifBLooseAdvantage(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        playerScores(P.B, 1);
        playerScores(P.A, 1);

        assertEquals("deuce", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_40_15_ifAScores3AndBScores1(){
        playerScores(P.A, 3);
        playerScores(P.B, 1);

        assertEquals("40:15", match.displayGameScore());
    }

    @Test
    public void shouldDisplayGameScore_15_40_ifAScores1AndBScores3(){
        playerScores(P.A, 1);
        playerScores(P.B, 3);


        assertEquals("15:40", match.displayGameScore());
    }

    @Test
    public void shouldResetGameScoreIfAWinsWithoutDeuce(){
        playerScores(P.A, 4);
        assertEquals("0:0", match.displayGameScore());
    }

    @Test
    public void shouldResetGameScoreIfBWinsWithoutDeuce(){
        playerScores(P.B, 4);
        assertEquals("0:0", match.displayGameScore());
    }

    @Test
    public void shouldResetGameScoreIfAWinsFromDeuce(){
        playerScores(P.A, 3);
        playerScores(P.B, 3);

        playerScores(P.A, 2);
        assertEquals("0:0", match.displayGameScore());
    }

    @Test
    public void shouldResetGameScoreIfBWinsFromDeuce(){
        playerScores(P.A, 3);
        playerScores(P.B, 5);
        assertEquals("0:0", match.displayGameScore());
    }

    @Test
    public void shouldDisplayInitalSetScore(){
        assertEquals("0-0", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_1_0_ifAWins1Game(){
        playerWinsGame_helper(P.A, 1);
        assertEquals("1-0", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_0_1_ifBWins1Game(){
        playerWinsGame_helper(P.B, 1);
        assertEquals("0-1", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_0_1to5_ifBWins1to6Games(){
        for (int i = 1; i <= 5; i++){
            playerWinsGame_helper(P.B, 1);
            assertEquals("0-" + i, match.displaySetScore());
        }
    }

    @Test
    public void shouldDisplaySetScore_1to5_0_ifAWins1to6Games(){
        for (int i = 1; i <= 5; i++) {
            playerWinsGame_helper(P.A, 1);
            assertEquals(i + "-0", match.displaySetScore());
        }
    }

    @Test
    public void shouldDisplaySetScore_5_5_ifPlayersWin5GamesEach(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        assertEquals("5-5", match.displaySetScore());

    }

    @Test
    public void shouldResetSetScoreifPlayerAWins6Games(){
        playerWinsGame_helper(P.A, 6);
        assertEquals("0-0", match.displaySetScore());
    }

    @Test
    public void shouldResetSetScoreifPlayerBWins6Games(){
        playerWinsGame_helper(P.B, 6);
        assertEquals("0-0", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_6_5_ifPlayersScore5EachAndThenAScores(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.A, 1);
        assertEquals("6-5", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_5_6_ifPlayersScore5EachAndThenBScores(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.B, 1);
        assertEquals("5-6", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_7_6_ifPlayersScore5A5B1A1B1A(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.A, 1);
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 1);
        assertEquals("7-6", match.displaySetScore());
    }

    @Test
    public void shouldDisplaySetScore_6_7_ifPlayersScore5A5B1A1B1B(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.A, 1);
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.B, 1);
        assertEquals("6-7", match.displaySetScore());
    }

    @Test
    public void shouldResetSetScoreifPlayersScore5A5B1A1B2A(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.A, 1);
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 2);
        assertEquals("0-0", match.displaySetScore());
    }

    @Test
    public void shouldResetSetScoreifPlayersScore5A5B1A1B2B(){
        playerWinsGame_helper(P.A, 5);
        playerWinsGame_helper(P.B, 5);
        playerWinsGame_helper(P.A, 1);
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.B, 2);
        assertEquals("0-0", match.displaySetScore());
    }

    @Test
    public void shouldDisplayInitialMatchScore(){
        assertEquals("0-0", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScoreEqualToFirstSetScore(){
        for (int i = 0; i < 5; i++){
            playerWinsGame_helper(P.A, 1);
            assertEquals((i + 1) + "-" + i, match.displayMatchScore());
            playerWinsGame_helper(P.B, 1);
            assertEquals((i + 1) + "-" + (i + 1), match.displayMatchScore());
        }
    }

    @Test
    public void shouldDisplayMatchScore_6_1__2_3_IfPlayersWin1B6A_2A3B(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 3);
        assertEquals("6-1,2-3", match.displayMatchScore());
    }


    @Test
    public void shouldDisplayMatchScore_6_1__2_6__0_0_IfPlayersWin1B6A_2A6B(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 6);
        assertEquals("6-1,2-6,0-0", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_61_26_63_46_64_IfPlayersWin1B6A_2A6B_3B6A_4A6B_4B6A(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 3);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 4);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 4);
        playerWinsGame_helper(P.A, 6);
        assertEquals("6-1,2-6,6-3,4-6,6-4", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_61_26_63_46_46_IfPlayersWin1B6A_2A6B_3B6A_4A6B_6B4A(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 3);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 4);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.A, 4);
        playerWinsGame_helper(P.B, 6);
        assertEquals("6-1,2-6,6-3,4-6,4-6", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_61_62_63IfPlayersWin1B6A_2B6A_3B6A(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.B, 2);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.B, 3);
        playerWinsGame_helper(P.A, 6);
        assertEquals("6-1,6-2,6-3", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_06_26_36IfPlayersWin6B_2A6B_3A6B(){
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.A, 3);
        playerWinsGame_helper(P.B, 6);
        assertEquals("0-6,2-6,3-6", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_61_62_36_63IfPlayersWin1B6A_2B6A_3A6B_3B6A(){
        playerWinsGame_helper(P.B, 1);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.B, 2);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 3);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 3);
        playerWinsGame_helper(P.A, 6);
        assertEquals("6-1,6-2,3-6,6-3", match.displayMatchScore());
    }

    @Test
    public void shouldDisplayMatchScore_06_26_63_36IfPlayersWin6B_2A6B_3B6A_3A6B(){
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.A, 2);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 3);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 3);
        playerWinsGame_helper(P.B, 6);
        assertEquals("0-6,2-6,6-3,3-6", match.displayMatchScore());
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfAScoresAfterMatchIsWonByB(){
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.A, 3);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfAScoresBfterMatchIsWonByB(){
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 6);
        playerWinsGame_helper(P.B, 3);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfAScoresAfterMatchIsWonByA(){
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 3);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowErrorIfAScoresBfterMatchIsWonByA(){
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.A, 6);
        playerWinsGame_helper(P.B, 3);
    }



    @After
    public void tearDown() {
        match = null;
    }

    private void playerScores(P player, int num){
        for (int i = 0; i < num; i++){
            if (player == P.A){
                match.playerAScores();
            } else if (player == P.B){
                match.playerBScores();
            }
        }
    }

    private void playerWinsGame_helper(P player, int num){
        for (int i = 0; i < num; i++){
            playerScores(player, 4);
        }
    }

    private void playerWinsSet_helper(P player, int num){
        for (int i = 0; i < num; i++){
            playerWinsGame_helper(player, 6);
        }
    }



}