(function ($) {
    "use strict";
    // v-btn-sidebar
    $('.v-btn-sidebar').click(function() {
        $('.v-sidebar').toggleClass('active');
    });


    $(document).ready(function ($) {
        $('.v-r-work').magnificPopup({
            type: 'image',

            // To invoke the popup
            // using the 'a' tag
            delegate: 'a',

            // Enable the gallery
            gallery: {
                enabled: true
            }
        });
    });


})(jQuery);