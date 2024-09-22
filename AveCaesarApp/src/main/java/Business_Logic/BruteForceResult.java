package Business_Logic;

public class BruteForceResult {
    private final String decryptedText;
    private final int bestKey;

    public BruteForceResult(String decryptedText, int bestKey) {
        this.decryptedText = decryptedText;
        this.bestKey = bestKey;
    }

    public String getDecryptedText() {
        return decryptedText;
    }

    public int getBestKey() {
        return bestKey;
    }
}
