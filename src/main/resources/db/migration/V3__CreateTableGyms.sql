CREATE TABLE gyms (
  id SERIAL PRIMARY KEY,
  title TEXT NOT NULL,
  description TEXT,
  phone TEXT,
  latitude DECIMAL(65,30) NOT NULL,
  longitude DECIMAL(65,30) NOT NULL
);