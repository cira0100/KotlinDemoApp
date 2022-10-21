﻿namespace Api.Interfaces
{
    public interface IDatabaseConnection
    {
        string ConnectionString { get; set; }
        string DatabaseName { get; set; }
        string ImageCollectionName { get; set; }
    }
}