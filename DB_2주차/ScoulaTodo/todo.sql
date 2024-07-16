use jdbc_ex;

create table jdbc_ex.todo (
       id integer auto_increment not null primary key,
       title varchar(128) not null,
       description varchar(512) null,
       done boolean,
       userid varchar(12) not null,
       foreign key (userid) references jdbc_ex.users(id)
                  on update cascade
                  on delete cascade
);

INSERT INTO jdbc_ex.todo(title, description, done, userid)
values ('야구장', '프로야구 경기도 봐야합니다.', false, 'guest'),
       ('놀기', '노는 것도 중요합니다.', false, 'guest'),
       ('Vue 학습', 'Vue 학습을 해야 합니다', false, 'member'),
       ('ES6 공부', 'ES6공부를 해야 합니다', true, 'guest');

select * from jdbc_ex.todo;