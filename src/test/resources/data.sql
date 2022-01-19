INSERT INTO platform(name) values('JOBDA');
INSERT INTO platform(name) values('JOBDA_CMS');

INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv-1', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('st', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv', 'http://server.com', 'http://client.com', 2);

INSERT INTO account(user_id, password, description, environment_id) values('test1', 'test1', 'JOBDA, dv', 1);
INSERT INTO account(user_id, password, description, environment_id) values('test2', 'test2', 'JOBDA, dv', 1);
INSERT INTO account(user_id, password, description, environment_id) values('test3', 'test3', 'JOBDA, dv-1', 2);
INSERT INTO account(user_id, password, description, environment_id) values('test4', 'test4', 'JOBDA, st', 3);
INSERT INTO account(user_id, password, description, environment_id) values('test5', 'test5', 'JOBDA_CMS, dv', 4);
INSERT INTO account(user_id, password, description, environment_id) values('test6', 'test6', 'JOBDA_CMS, dv', 4);
INSERT INTO account(user_id, password, description, environment_id) values('test7', 'test7', 'JOBDA_CMS, dv', 4);
INSERT INTO account(user_id, password, description, environment_id) values('test8', 'test8', 'JOBDA_CMS, dv', 4);