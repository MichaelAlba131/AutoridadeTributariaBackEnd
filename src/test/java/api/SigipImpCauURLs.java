package api;

public class SigipImpCauURLs {
    public enum URLs {
        HOME("http://sigipcauiws.tainfpautaldev.ritta.local:80/sigipcauiws/ws");

        private final String url;

        URLs(String url) {
            this.url = url;
        }

        public String getURLName() {
            return url;
        }

    }
}
