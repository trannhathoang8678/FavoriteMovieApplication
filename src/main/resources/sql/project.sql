
CREATE SCHEMA IF NOT EXISTS `springdemodb` DEFAULT CHARACTER SET utf8 ;
USE `springdemodb` ;
CREATE TABLE IF NOT EXISTS `MOVIE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `year_created` INT NULL,
  `url_poster` VARCHAR(45) NOT NULL,
  `overview` TEXT NOT NULL,
  `score` INT NULL,
  `length` VARCHAR(10) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `TYPE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `mydb`.`MOVIE_TYPE`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `MOVIE_TYPE` (
  `TYPE_id` INT NOT NULL,
  `MOVIE_id` INT NOT NULL,
  INDEX `fk_MOVIE_TYPE_TYPE_idx` (`TYPE_id` ASC),
  INDEX `fk_MOVIE_TYPE_MOVIE1_idx` (`MOVIE_id` ASC),
  CONSTRAINT `fk_MOVIE_TYPE_TYPE`
    FOREIGN KEY (`TYPE_id`)
    REFERENCES `TYPE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIE_TYPE_MOVIE1`
    FOREIGN KEY (`MOVIE_id`)
    REFERENCES `MOVIE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `VIEWER` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;



CREATE TABLE IF NOT EXISTS `MOVIE_DISPLAY` (
  `VIEWER_id` INT NOT NULL,
  `MOVIE_id` INT NOT NULL,
  `rank` INT NOT NULL,
  INDEX `fk_FAVORITE_MOVIE_MOVIE1_idx` (`MOVIE_id` ASC),
  INDEX `fk_MOVIE_DISPLAY_VIEWER1_idx` (`VIEWER_id` ASC),
  CONSTRAINT `fk_FAVORITE_MOVIE_MOVIE1`
    FOREIGN KEY (`MOVIE_id`)
    REFERENCES `MOVIE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_MOVIE_DISPLAY_VIEWER1`
    FOREIGN KEY (`VIEWER_id`)
    REFERENCES `VIEWER` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `POPULAR_PEOPLE` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `url_avatar` VARCHAR(45) NOT NULL,
  `biography` TEXT NULL,
  `gender` VARCHAR(10) NULL,
  `birthday` DATE NULL,
  `birthplace` VARCHAR(45) NULL,
  `popular_reason` VARCHAR(45) NULL,
  `number_credits` INT NOT NULL,
  `nickname` TEXT NULL,
  `url_social_media` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `PEOPLE_MOVIE` (
  `MOVIE_id` INT NOT NULL,
  `PEOPLE_id` INT NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  `time` INT NULL,
  INDEX `fk_PEOPLE_MOVIE_MOVIE1_idx` (`MOVIE_id` ASC),
  INDEX `fk_PEOPLE_MOVIE_POPULAR_PEOPLE1_idx` (`PEOPLE_id` ASC),
  CONSTRAINT `fk_PEOPLE_MOVIE_MOVIE1`
    FOREIGN KEY (`MOVIE_id`)
    REFERENCES `MOVIE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PEOPLE_MOVIE_POPULAR_PEOPLE1`
    FOREIGN KEY (`PEOPLE_id`)
    REFERENCES `POPULAR_PEOPLE` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;
