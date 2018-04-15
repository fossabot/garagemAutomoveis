package br.com.felipebatista.reservaautomoveis.util;

public class ResponseException {
    
    private Integer statusCode;
    private String type;
    private String rootCause;
    private String rootMessage;
    
    public static class Builder {
        
        private ResponseException builder;
        
        public Builder() {
            this.builder = new ResponseException();
        }
        
        public Builder(ResponseException builder) {
            this.builder = builder;
        }
        
        public static Builder create() {
            return new Builder();
        }
        
        public static Builder from(ResponseException builder) {
            return new Builder(builder);
        }
        
        public Builder statusCode(Integer statusCode) {
            this.builder.setStatusCode(statusCode);
            return this;
        }
        
        public Builder type(String type) {
            this.builder.setType(type);
            return this;
        }
        
        public Builder rootCause(String rootCause) {
            this.builder.setRootCause(rootCause);
            return this;
        }
        
        public Builder rootMessage(String rootMessage) {
            this.builder.setRootMessage(rootMessage);
            return this;
        }
        
        public ResponseException build() {
            return builder;
        }
        
    }
    
    public Integer getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getRootCause() {
        return rootCause;
    }
    
    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }
    
    public String getRootMessage() {
        return rootMessage;
    }
    
    public void setRootMessage(String rootMessage) {
        this.rootMessage = rootMessage;
    }
}
