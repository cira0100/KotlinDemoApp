using Api.Models;

namespace Api.Interfaces
{
    public interface IImageService
    {
        Image getImage(string id);
        Image getRandomImage();
        Image uploadImage(Image image);
    }
}