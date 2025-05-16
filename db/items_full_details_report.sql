select 
    i.item as item_name,
    u.name as user_name,
    r.role as user_role,
    c.category as category_name,
    s.state as item_state,
    cm.comment_text,
    a.attached as attachment,
    string_agg(ru.rule, ', ') as assigned_rules
from items i
join users u on i.user_id = u.id
join roles r on u.role_id = r.id
join categories c on i.category_id = c.id
join states s on i.states_id = s.id
left join comments cm on i.id = cm.item_id
left join attachs a on i.id = a.item_id
left join roles_rules rr on r.id = rr.role_id
left join rules ru on rr.rule_id = ru.id
group by i.id, u.id, r.id, c.id, s.id, cm.id, a.id
order by i.id;
