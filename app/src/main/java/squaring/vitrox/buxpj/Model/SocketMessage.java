package squaring.vitrox.buxpj.Model;

public class SocketMessage {
    private String t;
    private Body body;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }


    public class Body {
        private String securityId;
        private String currentPrice;

        public String getSecurityId() {
            return securityId;
        }

        public void setSecurityId(String securityId) {
            this.securityId = securityId;
        }

        public String getCurrentPrice() {
            return currentPrice;
        }

        public void setCurrentPrice(String currentPrice) {
            this.currentPrice = currentPrice;
        }
    }
}

