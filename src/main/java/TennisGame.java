public class TennisGame {
    private int pAGameScore = 0;
    private int pBGameScore = 0;


    /**
     * Adjusts Game counters when player A scores
     * @return <code>true</code> if Game is won by A with this score
     * <code>false</code> if the Game continues.
     */
    boolean playerAScores() {
        if ((pAGameScore == 4) || (pAGameScore == 3 && pBGameScore < 3)) {
            resetGameScore();
            return true;
        } else if (pBGameScore == 4){
            pBGameScore--;
        } else {
            pAGameScore++;
        }
        return false;
    }

    /**
     * Adjusts the current TennisGame counters when player A scores
     * @return <code>true</code> if the game is won by B with this score
     * <code>false</code> if the game continues.
     * */
    boolean playerBScores() {
        if ((pBGameScore == 4) || (pBGameScore == 3 && pAGameScore < 3)) {
            resetGameScore();
            return true;
        } else if (pAGameScore == 4){
            pAGameScore--;
        } else {
            pBGameScore++;
        }
        return false;
    }

    String getGameScore() {
        if (pAGameScore == 3 & pBGameScore == 3) {
            return "deuce";
        } else if (pAGameScore == 4 & pBGameScore == 3) {
            return ("pA_Adv");
        } else if (pAGameScore == 3 & pBGameScore == 4) {
            return ("pB_Adv");
        } else {
            return getGameScoreText(pAGameScore) + ':' + getGameScoreText(pBGameScore);
        }
    }

    private String getGameScoreText(int score) {
        switch (score) {
            case (0):
                return "0";
            case (1):
                return "15";
            case (2):
                return "30";
            case (3):
                return "40";
        }
        return "";
    }


    private void resetGameScore() {
        pAGameScore = 0;
        pBGameScore = 0;
    }
}
