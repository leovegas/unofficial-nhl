    (function () {
    function addQueryParamsToUrl(url, queryParams) {

        queryParams.forEach(param => {
            //   console.log(param);
            //   let params = new URLSearchParams(url.search.slice(1));
            //   console.log(params.has("utm_source"));

            if (!url.searchParams.has(param.key)) {
                console.log(url.searchParams.has(param.key));
                url.searchParams.append(param.key, param.value);
            }
        });
        return url;
    }

        $( ".t-btn.js-click-stat" ).click(function() {
            if ($(this).is('[href*=auth]')) {

            }
            else {

                console.log("but text "+ this.innerText);
                window.open("?"+"#popup:money","_self");
            }

        });


    $( ".t-btn.js-click-stat" ).click(function() {
    console.log("but text "+ this.innerText);
    window.open("?"+"#popup:money","_self");

});

    function addQueryParamsToLinks(queryParams) {
    var navLinks = document.querySelectorAll('a');

    navLinks.forEach(function (item) {
    if (item.host && item.host !== window.location.host) return;

    try {
    let url = new URL(item.href);
    if (url.protocol !== 'https:' && url.protocol !== 'http:') return;

    url = addQueryParamsToUrl(url, queryParams);
    item.href = url.href;
} catch (e) {}
});
}

    function addQueryParamsToLocation(queryParams) {
    let url = new URL(window.location.href);
    url = addQueryParamsToUrl(url, queryParams);

    window.history.replaceState({}, null, url.href);
}

    function getUtmParamsFromLocation() {
    const params = new URLSearchParams(window.location.search);
    console.log(window.location.search);
    const utmParams = [];
    params.forEach(function (value, key) {
    if (key.startsWith('utm_')) {
    utmParams.push({ key, value });
}
});
    return utmParams;
}

    function saveUtmParams(utmParams) {
    localStorage.setItem('utm', JSON.stringify(utmParams));
}

    function cleanUtm() {
    localStorage.clear();

}

    function getSavedParams() {
    const raw = localStorage.getItem('utm');
    return raw ? JSON.parse(raw) : null;
}

    function checkParams() {
    console.log("in checkparams");
    const utmParams = getUtmParamsFromLocation();

    if (!utmParams.length) {
    const savedUtmParams = getSavedParams();
    console.log(savedUtmParams);
    if (savedUtmParams) {
    addQueryParamsToLocation(savedUtmParams);
    addQueryParamsToLinks(savedUtmParams);
}
} else {
    saveUtmParams(utmParams);
    addQueryParamsToLinks(utmParams);
}
}

//   setInterval(function () {
//     checkParams();
//   }, 500);

    checkParams();
})();

    $(document).ready(function() {
    console.log("hash "+window.location.hash);
    $("body").append("<div class='r t-hidden'><a href='" + window.location.hash + "'></a></div>");
    setTimeout( function() {
    console.log("hash "+window.location.hash);

    $("a[href='" + window.location.hash + "']").click();
}, 500);
});

    $(document).ready(function(){
    $("a").each(function () {
        var href = $(this).attr("href");
        var search = "?" + window.location.search.split("&").filter(val => val.replace('?','').indexOf('s_') !== 0).join("&").replace('?','');
        console.log(window.location.search.split("&").length);
        if ( search !== "?" && href != undefined && href.includes(".") && !href.includes("#")) {
            console.log(href)
            if(!href.includes("?")) {
                $(this).attr("href", href + search);
            } else {
                if(!href.includes("utm_"))
                    $(this).attr("href", href + search.replace("?", "&"));

            }
        }
    });
});
