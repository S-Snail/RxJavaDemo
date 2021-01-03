package com.snail.cusretrofit.cus_retrofit;

public abstract class ParameterHandler {

    public abstract void apply(ServiceMethod serviceMethod, String value);

    public static class FiledParameterHandler extends ParameterHandler {

        private final String key;

        public FiledParameterHandler(String key) {
            this.key = key;
        }

        @Override
        public void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addFieldParameter(key,value);
        }
    }

    public static class QueryParameterHandler extends ParameterHandler {

        private final String key;

        public QueryParameterHandler(String key) {
            this.key = key;
        }

        @Override
        public void apply(ServiceMethod serviceMethod, String value) {
            serviceMethod.addQueryParameter(key,value);
        }
    }
}
