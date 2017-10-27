create table ibtrader_Config (
	uuid_ VARCHAR(75) null,
	configId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	name VARCHAR(500) null,
	value VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	globaldefault BOOLEAN,
	config_key VARCHAR(75) null,
	description STRING null
);

create table ibtrader_IBOrder (
	uuid_ VARCHAR(75) null,
	ordersId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	orderID LONG,
	shareID LONG,
	checked BOOLEAN
);

create table ibtrader_Market (
	uuid_ VARCHAR(75) null,
	marketId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	active_ BOOLEAN,
	createDate DATE null,
	modifiedDate DATE null,
	start_hour VARCHAR(75) null,
	end_hour VARCHAR(75) null,
	identifier VARCHAR(75) null,
	currency_ VARCHAR(75) null
);

create table ibtrader_Position (
	uuid_ VARCHAR(75) null,
	positionId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	shareId LONG,
	value DOUBLE,
	createDate DATE null,
	modifiedDate DATE null,
	state_ VARCHAR(75) null,
	state_in VARCHAR(75) null,
	state_out VARCHAR(75) null,
	description STRING null,
	price_in DOUBLE,
	price_real_in DOUBLE,
	limit_price_in DOUBLE,
	date_in DATE null,
	date_real_in DATE null,
	positionId_tws_out LONG,
	type_ VARCHAR(75) null,
	price_out DOUBLE,
	price_real_out DOUBLE,
	limit_price_out DOUBLE,
	date_out DATE null,
	date_real_out DATE null,
	share_number LONG,
	share_number_to_trade LONG,
	share_number_traded LONG,
	realtimeId_in LONG,
	realtimeId_out LONG,
	strategyId_in LONG,
	strategyId_out LONG,
	percentualstoplost_out DOUBLE,
	pricestoplost_out DOUBLE,
	percentualstopprofit_out DOUBLE,
	pricestopprofit_out DOUBLE,
	pendingcancelled LONG,
	trading_data_operations VARCHAR(75) null,
	simulation_mode BOOLEAN
);

create table ibtrader_Realtime (
	uuid_ VARCHAR(75) null,
	realtimeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	shareId LONG,
	value DOUBLE,
	createDate DATE null,
	modifiedDate DATE null,
	max_value DOUBLE,
	min_value BOOLEAN,
	volume INTEGER,
	avg_volume INTEGER
);

create table ibtrader_Share (
	uuid_ VARCHAR(75) null,
	shareId LONG not null primary key,
	name VARCHAR(500) null,
	symbol VARCHAR(75) null,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	active_ BOOLEAN,
	numbertopurchase LONG,
	percentual_limit_buy DOUBLE,
	percentual_stop_lost DOUBLE,
	percentual_stop_profit DOUBLE,
	percentual_stop_profit_position DOUBLE,
	expiry_date DATE null,
	expiry_expression VARCHAR(75) null,
	tick_futures DOUBLE,
	multiplier LONG,
	last_error_data_read VARCHAR(75) null,
	last_error_data_trade VARCHAR(75) null,
	security_type VARCHAR(75) null,
	exchange VARCHAR(75) null,
	primary_exchange VARCHAR(75) null,
	date_contract_verified DATE null,
	marketId LONG
);

create table ibtrader_Strategy (
	uuid_ VARCHAR(75) null,
	strategyID LONG not null primary key,
	groupId LONG,
	companyId LONG,
	name VARCHAR(500) null,
	description STRING null,
	createDate DATE null,
	modifiedDate DATE null,
	active_ BOOLEAN,
	status INTEGER,
	statusByUserId LONG,
	statusByUserName VARCHAR(75) null,
	statusDate DATE null,
	type_ VARCHAR(75) null,
	className VARCHAR(500) null,
	userId LONG
);

create table ibtrader_StrategyShare (
	uuid_ VARCHAR(75) null,
	strategyshareId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	strategyId LONG,
	shareId LONG
);