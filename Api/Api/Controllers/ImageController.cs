using Api.Interfaces;
using Api.Models;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling Web API for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ImageController : ControllerBase
    {

        private readonly IImageService _imageService;
        public ImageController(IImageService imageService)
        {
            _imageService = imageService;
        }



        // GET: api/<ImageController>
        [HttpGet]
        public Image Get()
        {
            return _imageService.getRandomImage();
        }

        // GET api/<ImageController>/5
        [HttpGet("{id}")]
        public Image Get(string id)
        {
            return _imageService.getImage(id);
        }

        // POST api/<ImageController>
        [HttpPost]
        public Image Post([FromBody] Image value)
        {
            return _imageService.uploadImage(value);
        }

    }
}
