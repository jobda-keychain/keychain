INSERT INTO platform(name) values('JOBDA');
INSERT INTO platform(name) values('JOBDA_CMS');

INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv-1', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('st', 'http://server.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv', 'http://server.com', 'http://client.com', current_date(), current_date(), 2);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv-2', 'https://api-jobda-im.kr-dv-jainwon.com', 'http://client.com', current_date(), current_date(), 1);
INSERT INTO environment(name, server_domain, client_domain, created_at, last_modified_at, platform_id) values('dv-3', 'https://api-jobda-im.kr-dv-jainwon.com', 'http://client.com', current_date(), current_date(), 1);

INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('test1', 'test1', 'JOBDA, dv', current_date(), current_date(), 1);
INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('test2', 'test2', 'JOBDA, dv', current_date(), current_date(), 1);
INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('test3', 'test3', 'JOBDA, dv-1', current_date(), current_date(), 2);
INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('test4', 'test4', 'JOBDA, st', current_date(), current_date(), 3);
INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('sasy0113', 'ssy0113', 'JOBDA, st', current_date(), current_date(), 5);
INSERT INTO account(account_id, password, description, created_at, last_modified_at, environment_id) values('test6', 'test6', 'JOBDA, st', current_date(), current_date(), 5);
