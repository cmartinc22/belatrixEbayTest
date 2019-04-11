package utils;

public class Enums {

    public enum ORDER_BY {
        ASC(1, "Ascending"),
        DESC(2, "Descending");

        private final int value;
        private final String orderName;

        ORDER_BY(int order, String name) {
            this.value = order;
            this.orderName = name;
        }

        public int getValue() {
            return this.value;
        }

        public String getOrderName() {
            return this.orderName;
        }
    }

    public enum LANGUAGE {
        ENGLISH(1, "English"),
        SPANISH(2, "Español");

        private final int value;
        private final String lagName;

        LANGUAGE(int order, String lagName) {
            this.value = order;
            this.lagName = lagName;
        }

        public int getValue() {
            return this.value;
        }

        public String getName() {
            return this.lagName;
        }
    }

    public enum STATUS {
        NEW_WITH_TAGS(1, "New with tags", "Nuevo"),
        NEW_WITHOUT_TAGS(1, "New without tags", "Nuevo (otro); consultar detalles"),
        NEW_WITH_DEFECTS(1, "New with defects", "Nuevo con defectos"),
        PRE_OWNED(1, "Pre-owned", "Usado"),
        NOT_SPECIFIED(1, "Not Specified", "Sin especificar");

        private final int value;
        private final String english;
        private final String spanish;

        STATUS(int order, String english, String spanish) {
            this.value = order;
            this.english = english;
            this.spanish = spanish;
        }

        public int getValue() {
            return this.value;
        }

        public String getSpanish() {
            return this.spanish;
        }

        public String getEnglish() {
            return this.english;
        }
    }

    public enum SORT {
        BEST_MATCH(1, "Best Match", "Mejor resultado"),
        TIME_ENDING_SOON(2, "Time: ending soonest", "Duración: primeros en finalizar"),
        TIME_NEWLY(3, "Time: newly listed", "Duración: anuncios más recientes"),
        PRICE_ASC(4, "Price + Shipping: lowest first", "Precio + Envío: más bajo primero"),
        PRICE_DESC(5, "Price + Shipping: highest first", "Precio + Envío: más alto primero"),
        DISTANCE(6, "Distance: nearest first", "Distancia: más cercanos primero");

        private final int value;
        private final String english;
        private final String spanish;

        SORT(int order, String english, String spanish) {
            this.value = order;
            this.english = english;
            this.spanish = spanish;
        }

        public int getValue() {
            return this.value;
        }

        public String getSpanish() {
            return this.spanish;
        }

        public String getEnglish() {
            return this.english;
        }
    }

}

