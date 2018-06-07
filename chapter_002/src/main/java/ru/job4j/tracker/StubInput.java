package ru.job4j.tracker;

/**
 * Class for auto input.
 * @author Roman Bednyashov (hipnorosva@gmail.com).
 * @since 0.1
 * @version 0.1
 */
public class StubInput implements Input {
    private  String[] answers;
    private int position = 0;

    public StubInput(String[] answers) {
        this.answers = answers;
    }

    public String ask(String question) {
        return answers[position++];
    }

    @Override
    public int ask(String question, int[] range) {
        throw new UnsupportedOperationException("UnsupportedOperation");
    }

}
