package models;

public class Data {
    private String title;
    private Card[] cards;

    public Data(String title, Card[] cards) {
        this.title = title;

    }

    // TODO Read about inner classes
    public class Card {
        private String question;
        private String answer;

        public Card(String question, String answer) {
            this.question = question;
            this.answer = answer;
        }

        public String getQuestion() {
            return question;
        }

        public void setQuestion(String question) {
            this.question = question;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }

    }
}
