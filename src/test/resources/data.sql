INSERT INTO platform(name) values('JOBDA');
INSERT INTO platform(name) values('JOBDA_CMS');

INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv-1', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('st', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv', 'http://server.com', 'http://client.com', current_date(), current_date(), 2);
INSERT INTO environment(id, name, server_domain, client_domain, created_at, last_modified_at, platform_id) values(5, 'pr', 'http://server.com', 'http://client.com', current_date(), current_date(), 2);

INSERT INTO account(user_id, password, description, created_at, last_modified_at, environment_id) values('test1', 'test1', 'JOBDA, dv', current_date(), current_date(), 1);
INSERT INTO account(user_id, password, description, created_at, last_modified_at, environment_id) values('test2', 'test2', 'JOBDA, dv', current_date(), current_date(), 1);
INSERT INTO account(user_id, password, description, created_at, last_modified_at, environment_id) values('test3', 'test3', 'JOBDA, dv-1', current_date(), current_date(), 2);
INSERT INTO account(user_id, password, description, created_at, last_modified_at, environment_id) values('test4', 'test4', 'JOBDA, st', current_date(), current_date(), 3);