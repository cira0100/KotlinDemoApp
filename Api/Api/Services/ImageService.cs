using Api.Interfaces;
using Api.Models;
using MongoDB.Driver;
using MongoDB.Driver.Linq;

namespace Api.Services
{
    public class ImageService : IImageService
    {
        private readonly IMongoCollection<Image> _images;
        public ImageService(IDatabaseConnection settings, IMongoClient mongoClient)
        {
            var database = mongoClient.GetDatabase(settings.DatabaseName);
            _images = database.GetCollection<Image>(settings.ImageCollectionName);

        }

        public Image getRandomImage()
        {
            return _images.AsQueryable().Sample(1).FirstOrDefault();
        }
        public Image getImage(string id)
        {
            return _images.Find(image => image._id == id).FirstOrDefault();
        }
        public Image uploadImage(Image image)
        {
            _images.InsertOne(image);
            return image;
        }
    }
}
