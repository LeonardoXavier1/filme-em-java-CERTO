package modal;

public class MovieInfos {
        private int id;
        private String title;
        private String overview;
        private String releaseDate;
        private double rating;

        public MovieInfos(int id, String title, String overview, String releaseDate, double rating) {
            this.id = id;
            this.title = title;
            this.overview = overview;
            this.releaseDate = releaseDate;
            this.rating = rating;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getOverview() {
            return overview;
        }

        public String getReleaseDate() {
            return releaseDate;
        }

        public double getRating() {
            return rating;
        }
    }
