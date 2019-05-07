INSERT INTO user(user_id, enabled, name, password, surname, username)
            VALUES(1, 1, 'Harry', 'pass', 'Potter', 'harryP');

INSERT INTO account(account_id, account_number, is_primary, account_type, account_balance, user_id)
            VALUES (1, 1, 1, 'PRIMARY', 2000, 1);
INSERT INTO account(account_id, account_number, is_primary, account_type, account_balance, user_id)
            VALUES (2, 2, 0, 'TRAVEL', 3000, 1);

INSERT INTO transaction(transaction_id, from_account_id, to_account_id, amount)
            VALUES (1, 1, 2, 300);