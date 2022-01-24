<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Carousel</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
/* Custom style to prevent carousel from being distorted 
   if for some reason image doesn't load */
.carousel-item{
    max-height: 600px;
}
</style>
</head>
<body>
<!-- <div class="container-lg my-3"> -->
    <div id="myCarousel" class="carousel slide" data-bs-ride="carousel">
        <!-- Carousel indicators -->
        <ol class="carousel-indicators">
            <li data-bs-target="#myCarousel" data-bs-slide-to="0" class="active"></li>
            <li data-bs-target="#myCarousel" data-bs-slide-to="1"></li>
            <li data-bs-target="#myCarousel" data-bs-slide-to="2"></li>
        </ol>
        
        <!-- Wrapper for carousel items -->
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img src="img/pexels-jéshoots-7405.jpg" class="d-block w-100 img-fluid" alt="Slide 1">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Availability</h5>
                    <p>All that you need is just a smartphone and an Internet connection</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/pexels-kai-pilger-462867.jpg" class="d-block w-100 img-fluid" alt="Slide 2">
                <div class="carousel-caption d-none d-md-block">
                    <h5>Professional drivers</h5>
                    <p>They are very knowledgeable about the city routes and can find the shortest route without breaking any traffic rules</p>
                </div>
            </div>
            <div class="carousel-item">
                <img src="img/pexels-sevenstorm-juhaszimrus-1605001.jpg" class="d-block w-100 img-fluid" alt="Slide 3">
                <div class="carousel-caption d-none d-md-block">
                    <h5>24/7 service</h5>
                    <p>As taxi services are available 24/7, you can enjoy a taxi ride any time or during transportation emergencies</p>
                </div>
            </div>
        </div>

        <!-- Carousel controls -->
        <a class="carousel-control-prev" href="#myCarousel" data-bs-slide="prev">
            <span class="carousel-control-prev-icon"></span>
        </a>
        <a class="carousel-control-next" href="#myCarousel" data-bs-slide="next">
            <span class="carousel-control-next-icon"></span>
        </a>
    </div>
<!-- </div> -->
</body>
</html>                            