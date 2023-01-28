let Utils = function() {

}

Utils.init = function() {

}

/**
 * get 요청
 * @param {string} url
 * @param {function} callbackFn
 * @param {boolean} async
 * @param {object} option
 * * @since 0106 init
 */
Utils.get = function(url, callbackFn, async=true, option){
    let opt = {
        url: url,
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        type: 'get',
        async: async,
        success: function (res) {
            callbackFn(res);
        }
    };


    if(Pp.isNotNull(option)){
        opt = Pp.extend(opt, option);
    }


    $.ajax(opt);

}

/**
 * post 요청
 * @param {string} url
 * @param {object} formData
 * @param {function} callbackFn
 * @param {boolean} async
 * @param {object} option
 * * @since 0106 init
 */
Utils.post = function(url, formData, callbackFn, async=true, option){
    let opt = {
        url: url,
        async: async,
        data: formData,
        method: 'POST',
        contentType: false,
        processData: false,
    };


    if(Pp.isNotNull(option)){
        opt = Pp.extend(opt, option);
    }


    $.ajax(opt)
        .done(callbackFn)
        .fail(res=>{
            stopLoading();
            console.log(res);
            toastr.error('오류가 발생했습니다.');
        });



}


/**
 * put 요청
 * @param {string} url
 * @param {object} formData
 * @param {function} callbackFn
 * @param {boolean} async
 * @param {object} option
 * * @since 0106 init
 */
Utils.put = function(url, formData, callbackFn, async=true, option){
    let opt = {
        url: url,
        async: async,
        data: formData,
        method: 'PUT',
        contentType: false,
        processData: false,
    };


    if(Pp.isNotNull(option)){
        opt = Pp.extend(opt, option);
    }


    $.ajax(opt)
        .done(callbackFn)
        .fail(res=>{
            console.log(res);
            toastr.error('오류가 발생했습니다.');
        });

}


/**
 * delete 요청
 * @param {string} url
 * @param {object} param
 * @param {function} callbackFn
 * @param {boolean} async
 * @param {object} option
 * @since 0106 init
 */
Utils.delete = function(url, param, callbackFn, async=true, option){
    let opt = {
        url: url,
        async: async,
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        method: 'DELETE',
    };


    if(Pp.isNotNull(option)){
        opt = Pp.extend(opt, option);
    }


    $.ajax(opt)
        .done(callbackFn)
        .fail(res=>{
            console.log(res);
            toastr.error('오류가 발생했습니다.');
        });

}

/**
 * 페이징 관련 처리
 * 	1. 페이징 계산
 * 	2. 계산 결과로 화면에 페이징 관련 html 표시
 * 	3. 페이지 번호 클릭시 콜백함수 호출
 * @param {number} totalItems 전체 아이탬 갯수
 * @param {number} currentPage 현재 페이지 번호
 * @param {Node} $el html를 표시할 엘리먼트
 * @param {Function} callbackFn 페이지 번호 클릭시 호출할 콜백함수
 * @param {object} option {'pageSize', 'maxPages'}
 */

Utils.paginate = function(totalItems) {
    var currentPage = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : 1;
    var pageSize = arguments.length > 2 && arguments[2] !== undefined ? arguments[2] : 10;
    var maxPages = arguments.length > 3 && arguments[3] !== undefined ? arguments[3] : 10;


    // calculate total pages
    var totalPages = Math.ceil(totalItems / pageSize);

    // ensure current page isn't out of range
    if (currentPage < 1) {
        currentPage = 1;
    } else if (currentPage > totalPages) {
        currentPage = totalPages;
    }

    var startPage = void 0,
        endPage = void 0;
    if (totalPages <= maxPages) {
        // total pages less than max so show all pages
        startPage = 1;
        endPage = totalPages;
    } else {
        // total pages more than max so calculate start and end pages
        var maxPagesBeforeCurrentPage = Math.floor(maxPages / 2);
        var maxPagesAfterCurrentPage = Math.ceil(maxPages / 2) - 1;
        if (currentPage <= maxPagesBeforeCurrentPage) {
            // current page near the start
            startPage = 1;
            endPage = maxPages;
        } else if (currentPage + maxPagesAfterCurrentPage >= totalPages) {
            // current page near the end
            startPage = totalPages - maxPages + 1;
            endPage = totalPages;
        } else {
            // current page somewhere in the middle
            startPage = currentPage - maxPagesBeforeCurrentPage;
            endPage = currentPage + maxPagesAfterCurrentPage;
        }
    }

    // calculate start and end item indexes
    var startIndex = (currentPage - 1) * pageSize;
    var endIndex = Math.min(startIndex + pageSize - 1, totalItems - 1);

    // create an array of pages to ng-repeat in the pager control
    var pages = Array.from(Array(endPage + 1 - startPage).keys()).map(function (i) {
        return startPage + i;
    });

    // return object with all pager properties required by the view
    return {
        totalItems: totalItems,
        currentPage: currentPage,
        pageSize: pageSize,
        totalPages: totalPages,
        startPage: startPage,
        endPage: endPage,
        startIndex: startIndex,
        endIndex: endIndex,
        pages: pages
    };
}

Utils.pagination = function(totalItems, currentPage, $el, callbackFn, option){
    let opt = Pp.extend({'pageSize':10, 'maxPages':5}, option);

    //
    let pageSize = opt.pageSize ? opt.pageSize : 10;
    let maxPages = opt.maxPages ? opt.maxPages : 5;

    let paging = this.paginate(totalItems, currentPage, pageSize, maxPages);


    let s = '';
    s += '<ul class="pagination">';

    //
    s += '	<li class="ico first" data-page-no="'+paging.startPage+'">처음</li>';

    //
    for(let i=0; i<paging.pages.length; i++){
        let pageNo = paging.pages[i];

        //
        if(currentPage == pageNo){
            s += '	<li class="on"	data-page-no="'+pageNo+'">'+pageNo+'</li>';
        } else {
            s += '	<li class=""	data-page-no="'+pageNo+'">'+pageNo+'</li>';
        }
    }

    //
    if(0 === paging.pages.length) {
        s += '	<li class=""	data-page-no="1">1</li>';
    }

    //
    s += '	<li class="ico end" data-page-no="'+paging.totalPages+'">마지막</li>';

    s += '</ul>';

    //화면에 표시
    $el.html(s);

    //페이지 클릭 이벤트 dhkim added wrapperDiv 20210119
    $el.find('ul.pagination > li').click(function(){
        $el.find('ul.pagination > li').removeClass('on');
        //
        let pageNo = $(this).data('page-no');
        //
        $el.find('ul.pagination > li[data-page-no="'+pageNo+'"]').addClass('on');
        //
        callbackFn(pageNo);
    });
};
