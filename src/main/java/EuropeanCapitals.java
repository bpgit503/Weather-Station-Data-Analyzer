import lombok.Getter;

@Getter
public enum EuropeanCapitals {
    VIENNA("Vienna", "Austria"),
    BRUSSELS("Brussels", "Belgium"),
    SOFIA("Sofia", "Bulgaria"),
    ZAGREB("Zagreb", "Croatia"),
    NICOSIA("Nicosia", "Cyprus"),
    PRAGUE("Prague", "Czech Republic"),
    COPENHAGEN("Copenhagen", "Denmark"),
    TALLINN("Tallinn", "Estonia"),
    HELSINKI("Helsinki", "Finland"),
    PARIS("Paris", "France"),
    BERLIN("Berlin", "Germany"),
    ATHENS("Athens", "Greece"),
    BUDAPEST("Budapest", "Hungary"),
    REYKJAVIK("Reykjavik", "Iceland"),
    DUBLIN("Dublin", "Ireland"),
    ROME("Rome", "Italy"),
    RIGA("Riga", "Latvia"),
    VILNIUS("Vilnius", "Lithuania"),
    LUXEMBOURG("Luxembourg", "Luxembourg"),
    VALLETTA("Valletta", "Malta"),
    CHISINAU("Chisinau", "Moldova"),
    MONACO("Monaco", "Monaco"),
    PODGORICA("Podgorica", "Montenegro"),
    AMSTERDAM("Amsterdam", "Netherlands"),
    OSLO("Oslo", "Norway"),
    WARSAW("Warsaw", "Poland"),
    LISBON("Lisbon", "Portugal"),
    BUCHAREST("Bucharest", "Romania"),
    MOSCOW("Moscow", "Russia"),
    BELGRADE("Belgrade", "Serbia"),
    BRATISLAVA("Bratislava", "Slovakia"),
    LJUBLJANA("Ljubljana", "Slovenia"),
    MADRID("Madrid", "Spain"),
    STOCKHOLM("Stockholm", "Sweden"),
    BERN("Bern", "Switzerland"),
    KIEV("Kyiv", "Ukraine"),
    LONDON("London", "United Kingdom");

    private final String city;
    private final String country;

    EuropeanCapitals(String city, String country) {
        this.city = city;
        this.country = country;
    }
}