public class TennisMatch {

    private String finishedSetsScore = null;
    private TennisSet currentSet = new TennisSet();

    private int pAMatchScore = 0;
    private int pBMatchScore = 0;
    private boolean isMatchFinished = false;

    public void playerAScores() {
        if (isMatchFinished){
            throw new RuntimeException("Hey, match is finished already! Stop swinging your racquet!");
        } else if (currentSet.playerAScores()){
            pAWinsSet();
        }
    }

    public void playerBScores() {
        if (isMatchFinished){
            throw new RuntimeException("Hey, match is finished already! Stop swinging your racquet!");
        } else if (currentSet.playerBScores()){
            pBWinsSet();
        }
    }

    public String displayGameScore() {
        return currentSet.getCurrentGame().getGameScore();
    }

    public String displaySetScore() {
        return currentSet.getSetScore();
    }

    public String displayMatchScore() {
        return finishedSetsScore == null ?
                currentSet.getSetScore() :
                finishedSetsScore + ',' + currentSet.getSetScore();
    }


    private void resetCurrentSet(){
        finishedSetsScore = (finishedSetsScore == null) ?
                currentSet.getSetScore() :
                finishedSetsScore + ',' + currentSet.getSetScore();
        currentSet.resetSetScore();
    }

    private void pAWinsSet() {
        pAMatchScore++;
        if (pAMatchScore == 3){
            isMatchFinished = true;
            return;
        }
        resetCurrentSet();
    }

    private void pBWinsSet() {
        pBMatchScore++;
        if (pBMatchScore == 3){
            isMatchFinished = true;
            return;
        }
        resetCurrentSet();
    }



}
