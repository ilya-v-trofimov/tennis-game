public class TennisSet {

    private int pASetScore = 0;
    private int pBSetScore = 0;

    private TennisGame currentGame = new TennisGame();

    /**
     * @return <code>true</code> if player A wins the set
     * <code>false</code> otherwise
     * */
    boolean playerAScores() {
        return currentGame.playerAScores() && pAWinsGame();
    }

    /**
     * @return <code>true</code> if player B wins the set
     * <code>false</code> otherwise
     * */
    boolean playerBScores() {
        return currentGame.playerBScores() && pBWinsGame();

    }

    TennisGame getCurrentGame() {
        return currentGame;
    }

    String getSetScore() {
        return pASetScore + "-" + pBSetScore;
    }

    void resetSetScore(){
        pASetScore = 0;
        pBSetScore = 0;
    }

    private boolean pAWinsGame() {
        pASetScore++;
        return pASetScore >= 6 && (pASetScore - pBSetScore) >= 2;
    }

    private boolean pBWinsGame() {
        pBSetScore++;
        return pBSetScore >= 6 && (pBSetScore - pASetScore) >= 2;

    }

}
