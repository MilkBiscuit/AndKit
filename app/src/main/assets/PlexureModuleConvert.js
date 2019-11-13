
var offerExample = {
    "active": true,
    "burtCount": 0,
    "description": "qqq",
    "endDate": "2029 Nov 06 00:00:00",
    "id": 220,
    "startDate": "2019 Oct 01 00:00:00",
    "title": "aaa",
    "tags": [],
    "venueIds": [2417],
    "weight": 0
}

function convertOffer(offer) {
    return {
        "title": offer.title,
        "subTitle": offer.description
    };
}

function convertExample() {
    const convertedOffer = convertOffer(offerExample);

    return JSON.stringify(convertedOffer);
}

