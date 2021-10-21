-- FUNCTION: public.get_todo_by_id(bigint)

-- DROP FUNCTION public.get_todo_by_id(bigint);

CREATE OR REPLACE FUNCTION public.get_todo_by_id(
	l_id bigint)
    RETURNS refcursor
    LANGUAGE 'plpgsql'
    COST 100
    VOLATILE PARALLEL UNSAFE
AS $BODY$
DECLARE L_DATA REFCURSOR :='data';
BEGIN OPEN L_DATA FOR
SELECT D.ID,
	D.TASK_NAME,
	D.TASK_CONTEXT,
	D.CREATE_DATE,
	D.DEADLINE
FROM TODO_DATA D where d.id=l_id;
return L_DATA;
end;
$BODY$;

ALTER FUNCTION public.get_todo_by_id(bigint)
    OWNER TO postgres;