﻿using Api.Interfaces;

namespace Api.Database
{
    public class DatabaseConnection : IDatabaseConnection
    {
        public string ConnectionString { get; set; } = String.Empty;
        public string DatabaseName { get; set; } = String.Empty;
        public string ImageCollectionName { get; set; } = String.Empty;
    }
}
