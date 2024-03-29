USE [kltn_appium]
GO
/****** Object:  Table [dbo].[Login]    Script Date: 23/05/2023 11:20:40 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Login](
	[id] [int] NOT NULL,
	[TCs_description] [nvarchar](100) NULL,
	[email] [nvarchar](50) NULL,
	[password] [nchar](8) NULL,
	[expected result] [nvarchar](100) NULL,
	[actual_result] [nvarchar](10) NULL,
	[tester] [nvarchar](30) NULL,
	[datetime] [datetime] NULL,
	[file_log] [nvarchar](100) NULL,
 CONSTRAINT [PK_Login] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Login_null]    Script Date: 23/05/2023 11:20:40 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Login_null](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[TCs_decripton] [nvarchar](50) NULL,
	[username] [nvarchar](50) NULL,
	[password] [nchar](10) NULL,
	[actual_result] [nvarchar](50) NULL,
	[tester] [nvarchar](50) NULL,
	[datetime] [datetime] NULL,
 CONSTRAINT [PK_Login_null] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[SignUp]    Script Date: 23/05/2023 11:20:40 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[SignUp](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[testcase] [nvarchar](100) NULL,
	[email] [nvarchar](50) NULL,
	[name] [nvarchar](50) NULL,
	[phone_number] [nchar](10) NULL,
	[password] [nvarchar](10) NULL,
	[expected_result] [nvarchar](100) NULL,
	[actual_result] [nvarchar](6) NULL,
	[tester] [nvarchar](30) NULL,
	[datetime] [datetime] NULL,
	[file_log] [varbinary](max) NULL,
 CONSTRAINT [PK_SignUp] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (1, N'Password invalid', N'111@gmail.com', N'0101    ', N'f', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T09:31:51.000' AS DateTime), N'D:\kltn_11/screenshot/21_04_2023 11_03_06.png')
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (2, N'Email invalid', N'123@@gmail.com', N'12345678', N'f', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T09:32:00.000' AS DateTime), NULL)
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (3, N'Bỏ trống pasword', N'123@gmail.com', NULL, N'f', N'PASS', N'Đặng Lý', CAST(N'2023-04-22T09:32:09.000' AS DateTime), N'D:\kltn_11/screenshot/22_04_2023 09_32_18.png')
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (4, N'Bỏ trống email', NULL, N'123456  ', N'f', NULL, NULL, NULL, NULL)
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (5, N'Password=space', N'123@gmail.com', N'        ', N'f', NULL, NULL, NULL, NULL)
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (6, N'Login success', N'123@gmail.com', N'12345678', N'p', NULL, NULL, NULL, NULL)
INSERT [dbo].[Login] ([id], [TCs_description], [email], [password], [expected result], [actual_result], [tester], [datetime], [file_log]) VALUES (7, N'', N'             ', N'12345678', N'f', NULL, NULL, NULL, NULL)
GO
SET IDENTITY_INSERT [dbo].[Login_null] ON 

INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (1, N'null p', N'abc', NULL, N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:03.000' AS DateTime))
INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (2, N'null email', NULL, N'123465    ', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:10.000' AS DateTime))
INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (3, N'null 2', NULL, NULL, N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:16.000' AS DateTime))
INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (4, N'invalid username', N'gggg', N'123465    ', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:26.000' AS DateTime))
INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (5, N'invalid password', N'haily@gmail.com', N'121212    ', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:35.000' AS DateTime))
INSERT [dbo].[Login_null] ([id], [TCs_decripton], [username], [password], [actual_result], [tester], [datetime]) VALUES (6, N'oke', N'haily@gmail.com', N'12345678  ', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:48:44.000' AS DateTime))
SET IDENTITY_INSERT [dbo].[Login_null] OFF
GO
SET IDENTITY_INSERT [dbo].[SignUp] ON 

INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (1, N'Kiểm tra bỏ trống trường Email', NULL, N'ly', N'0123456789', N'12345678', N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:01.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (2, N'Kiểm tra bỏ trống trường Name', N'haily@gmail.com', NULL, N'0123456789', N'12345678', N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:09.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (3, N'Kiểm tra bỏ trống trường Phone Number', N'haily@gmail.com', N'ly', NULL, N'12345678', N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:16.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (4, N'Kiểm tra bỏ trống all', NULL, NULL, NULL, NULL, N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:22.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (7, N'Kiểm tra chỉ nhập trường Name', NULL, N'ly', NULL, NULL, N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:28.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (8, N'Kiểm tra chỉ nhập trường Phone Number', NULL, NULL, N'0123456789', NULL, N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:35.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (9, N'Kiểm tra chỉ nhập trường Password', NULL, NULL, NULL, N'12345678', N'Please fill in the form correctly', N'FAILED', N'Đặng Lý', CAST(N'2023-04-22T16:47:43.000' AS DateTime), NULL)
INSERT [dbo].[SignUp] ([id], [testcase], [email], [name], [phone_number], [password], [expected_result], [actual_result], [tester], [datetime], [file_log]) VALUES (10, N'Kiểm tra nhập các thông tin hợp lệ', N'123@gmail.com', N'Đặng Lý', N'0123456789', N'123456', N'Registration Succeeded', N'PASS', N'Đặng Lý', CAST(N'2023-04-22T16:47:54.000' AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[SignUp] OFF
GO
