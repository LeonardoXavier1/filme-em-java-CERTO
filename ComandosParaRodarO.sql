"https://prnt.sc/VbJjDnmfaTjX"  /*--> TABLE LINK movies;*/
"https://prnt.sc/s5dgdVfbTCdI"  /*--> TABLE LINK users;*/

create database meubanco;

use meubanco;

--> Create the users movies
CREATE TABLE movies (
  id INT AUTO_INCREMENT PRIMARY KEY,
  user VARCHAR(50) NOT NULL,
  title VARCHAR(100) NOT NULL,
  overview TEXT NOT NULL,
  release_date DATE,
  rating DECIMAL(3,1)
);

--> Create the users table
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(50) NOT NULL,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL
);

select * from users;

--> Insert initial data, there is a password column because I thought it would be necessary and etc... but after you explained it better I just ended up not using it anyway
INSERT INTO users (name, email, password) VALUES
  ('Leo', 'leo@example.com', 'password123'),
  ('Pedro', 'pedro@example.com', 'password456');