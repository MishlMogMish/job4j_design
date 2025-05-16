insert into roles (role)
values
('vip_customer'),
('client'),
('company_manager');

insert into "rules" (rule)
values
('gift'),
('discount'),
('standard_terms');

insert into categories (category)
values
('Books'),
('Electronics'),
('Music');

insert into states (state)
values
('purchased'),
('in transit'),
('delivered');

insert into "users" ("name", role_id)
values
('Ivan', 1),
('Petr', 2),
('Katya', 3);

insert into items (item, user_id, category_id, states_id)
values
('book', 1, 1, 1),
('laptop', 2, 2, 2),
('guitar', 3, 3, 3);

insert into comments (comment_text, item_id)
values
('Excellent!', 1),
('Okay', 3),
('Didn''t like it much', 2);

insert into attachs (attached, item_id)
values
('photo1.jpg', 2),
('photo2.jpg', 3),
('photo3.jpg', 1);

insert into roles_rules (rule_id, role_id)
values
(1, 1),
(1, 2),
(2, 2),
(2, 1),
(3, 1),
(3, 2),
(3, 3);







