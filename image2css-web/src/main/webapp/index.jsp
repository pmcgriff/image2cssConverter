<!DOCTYPE html>
<html lang="en">
    <head>
        <title>image2css converter</title>

        <script type="text/javascript" src="assets/js/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="assets/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="assets/js/image2css.js"></script>
        <script type="text/javascript" src="assets/js/prettify.js"></script>
        <script type="text/javascript" src="assets/js/lang-css.js"></script>

        <link href="assets/css/image2css.css" rel="stylesheet">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/css/prettify.css" rel="stylesheet">
    </head>

    <body>
        <div class="container">

            <div class="row row-first-padded">
                <div class="col-md-12">
                    <a class="" href="https://github.com/mhuckaby/image2cssConverter">
                        <img src="assets/img/image2css.png" />
                    </a>
                    <span>
                        <h2 style="margin-top:7px;">&nbsp;image2css converter</h2>
                    </span>
                </div>
            </div>

            <div class="row row-padded">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <span>Provide a URL to an image file to be converted into a CSS data-URI</span>
                        </div>

                        <div class="panel-body">
                            <div class="input-group">
                                <input id="targetUrl" type="text" class="form-control" placeholder="http://" />
                                <div class="input-group-btn">
                                    <button id="convertUrl" type="button" class="btn btn-default" tabindex="-1">Convert</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="demo-result" class="row row-padded" style="display:none;">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <span id="conversionRequestAwaitingResponse"><img src="assets/img/ajax-loader.gif">&nbsp;One moment, please ...</span>
                            <span id="conversionRequestReceivedResponse" class="hidden">Conversion Result</span>
                        </div>
                        <div class="panel-body" style="overflow-x:auto">
                            <div id="responseStatus"></div>
                            <pre id="responseBody" class="prettyprint lang-css"></pre>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <a href="https://github.com/mhuckaby/image2cssConverter"><img id="git-fork-ribbon" src="https://s3.amazonaws.com/github/ribbons/forkme_right_red_aa0000.png" alt="Fork me on GitHub"></a>
    </body>
    <script>
        context(jQuery, "c", prettyPrintOne);
    </script>
</html>
