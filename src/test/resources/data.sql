INSERT INTO platform(name) values('JOBDA');
INSERT INTO platform(name) values('JOBDA_CMS');

INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv-1', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('st', 'http://server.com', 'http://client.com', 1);
INSERT INTO environment(name, server_domain, client_domain, platform_id) values('dv', 'http://server.com', 'http://client.com', 2);

INSERT INTO account(user_id, password, description, environment_id) values('test1', 'test1', 'testtest', 1);
INSERT INTO account(user_id, password, description, environment_id) values('test2', 'test2', 'testtest', 1);
INSERT INTO account(user_id, password, description, environment_id) values('test3', 'test3', 'testtest', 2);
INSERT INTO account(user_id, password, description, environment_id) values('test4', 'test4', 'testtest', 3);