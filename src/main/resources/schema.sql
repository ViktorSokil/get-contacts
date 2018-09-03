insert into contact values ( generate_series(1,1000000), md5(random()::text));




