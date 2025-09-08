-- --------------------------------------------------------
-- Máy chủ:                      127.0.0.1
-- Server version:               8.0.39-0ubuntu0.22.04.1 - (Ubuntu)
-- Server OS:                    Linux
-- HeidiSQL Phiên bản:           12.5.0.6677
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for java_coffee
CREATE DATABASE IF NOT EXISTS `java_coffee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `java_coffee`;

-- Dumping structure for table java_coffee.ChiTietHoaDon
CREATE TABLE IF NOT EXISTS `ChiTietHoaDon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `so_luong` int NOT NULL,
  `SanPham_id` int NOT NULL,
  `HoaDon_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_OrderDetail_Products1_idx` (`SanPham_id`),
  KEY `fk_OrderDetail_Order1_idx` (`HoaDon_id`),
  CONSTRAINT `fk_OrderDetail_Order1` FOREIGN KEY (`HoaDon_id`) REFERENCES `HoaDon` (`id`),
  CONSTRAINT `fk_OrderDetail_Products1` FOREIGN KEY (`SanPham_id`) REFERENCES `SanPham` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.ChiTietHoaDon: ~0 rows (approximately)

-- Dumping structure for table java_coffee.ChiTietPhieuNhap
CREATE TABLE IF NOT EXISTS `ChiTietPhieuNhap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `PhieuNhap_id` int NOT NULL,
  `NguyenLieu_id` int NOT NULL,
  `so_luong` float NOT NULL,
  `don_vi` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'KG',
  `don_gia` float NOT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ChiTietPhieuNhap_PhieuNhap1_idx` (`PhieuNhap_id`),
  KEY `fk_ChiTietPhieuNhap_NguyenLieu1_idx` (`NguyenLieu_id`),
  CONSTRAINT `fk_ChiTietPhieuNhap_NguyenLieu1` FOREIGN KEY (`NguyenLieu_id`) REFERENCES `NguyenLieu` (`id`),
  CONSTRAINT `fk_ChiTietPhieuNhap_PhieuNhap1` FOREIGN KEY (`PhieuNhap_id`) REFERENCES `PhieuNhap` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table java_coffee.ChiTietPhieuNhap: ~0 rows (approximately)

-- Dumping structure for table java_coffee.ChiTietPhieuXuat
CREATE TABLE IF NOT EXISTS `ChiTietPhieuXuat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `NguyenLieu_id` int NOT NULL,
  `so_luong` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `don_vi` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'KG',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  `PhieuXuat_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_ChiTietPhieuXuat_NguyenLieu1_idx` (`NguyenLieu_id`),
  KEY `fk_ChiTietPhieuXuat_PhieuXuat1_idx` (`PhieuXuat_id`),
  CONSTRAINT `fk_ChiTietPhieuXuat_NguyenLieu1` FOREIGN KEY (`NguyenLieu_id`) REFERENCES `NguyenLieu` (`id`),
  CONSTRAINT `fk_ChiTietPhieuXuat_PhieuXuat1` FOREIGN KEY (`PhieuXuat_id`) REFERENCES `PhieuXuat` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table java_coffee.ChiTietPhieuXuat: ~0 rows (approximately)

-- Dumping structure for table java_coffee.ChucNang
CREATE TABLE IF NOT EXISTS `ChucNang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_cn` varchar(45) NOT NULL,
  `mo_ta` varchar(45) DEFAULT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.ChucNang: ~51 rows (approximately)
INSERT INTO `ChucNang` (`id`, `ten_cn`, `mo_ta`, `is_active`, `createdAt`) VALUES
	(1, 'ADMIN', 'full chức năng', 1, '2024-04-29 21:13:30'),
	(2, 'READ_USER', 'xem danh sách tất cả người dùng', 1, '2024-04-29 21:13:30'),
	(3, 'CREATE_USER', 'tạo người dùng mới', 1, '2024-04-29 21:13:30'),
	(4, 'UPDATE_USER', 'chỉnh sửa người dùng', 1, '2024-04-29 21:13:30'),
	(5, 'DELETE_USER', 'khoá tài khoản người dùng', 1, '2024-04-29 21:13:30'),
	(6, 'IMPORT_USER', 'tạo người dùng bằng import file excel', 1, '2024-04-29 21:13:30'),
	(7, 'EXPORT_USER', 'xuất danh sách tất cả người dùng', 1, '2024-04-29 21:13:30'),
	(8, 'READ_PRODUCT', 'xem danh sách tất cả sản phẩm', 1, '2024-04-29 21:13:30'),
	(9, 'CREATE_PRODUCT', 'tạo sản phẩm mới', 1, '2024-04-29 21:13:30'),
	(10, 'UPDATE_PRODUCT', 'chỉnh sửa sản phẩm', 1, '2024-04-29 21:13:30'),
	(11, 'DELETE_PRODUCT', 'khoá tài khoản sản phẩm', 1, '2024-04-29 21:13:30'),
	(12, 'IMPORT_PRODUCT', 'tạo sản phẩm bằng import file excel', 1, '2024-04-29 21:13:30'),
	(13, 'EXPORT_PRODUCT', 'xuất danh sách tất cả sản phẩm', 1, '2024-04-29 21:13:30'),
	(14, 'READ_BILL', 'xem danh sách tất cả đơn hàng', 1, '2024-04-29 21:13:30'),
	(15, 'CREATE_BILL', 'tạo đơn hàng mới', 1, '2024-04-29 21:13:30'),
	(16, 'UPDATE_BILL', 'chỉnh sửa đơn hàng', 1, '2024-04-29 21:13:30'),
	(17, 'DELETE_BILL', 'khoá tài khoản đơn hàng', 1, '2024-04-29 21:13:30'),
	(18, 'IMPORT_BILL', 'tạo đơn hàng bằng import file excel', 1, '2024-04-29 21:13:30'),
	(19, 'EXPORT_BILL', 'xuất danh sách tất cả đơn hàng', 1, '2024-04-29 21:13:30'),
	(20, 'READ_WAREHOUSE', 'xem danh sách tất cả kho nguyên liệu', 1, '2024-04-29 21:13:30'),
	(21, 'CREATE_WAREHOUSE', 'tạo kho nguyên liệu mới', 1, '2024-04-29 21:13:30'),
	(22, 'UPDATE_WAREHOUSE', 'nhập kho nguyên liệu', 1, '2024-04-29 21:13:30'),
	(23, 'DELETE_WAREHOUSE', 'khoá tài khoản kho nguyên liệu', 1, '2024-04-29 21:13:30'),
	(24, 'IMPORT_WAREHOUSE', 'tạo kho nguyên liệu bằng import file excel', 1, '2024-04-29 21:13:30'),
	(25, 'EXPORT_WAREHOUSE', 'xuất danh sách tất cả kho nguyên liệu', 1, '2024-04-29 21:13:30'),
	(26, 'READ_EMPLOYEE', 'xem danh sách tất cả nhân viên', 1, '2024-04-29 21:13:30'),
	(27, 'CREATE_EMPLOYEE', 'tạo nhân viên mới', 1, '2024-04-29 21:13:30'),
	(28, 'UPDATE_EMPLOYEE', 'chỉnh sửa nhân viên', 1, '2024-04-29 21:13:30'),
	(29, 'DELETE_EMPLOYEE', 'xoá tài khoản nhân viên', 1, '2024-04-29 21:13:30'),
	(30, 'IMPORT_EMPLOYEE', 'tạo nhân viên bằng import file excel', 1, '2024-04-29 21:13:30'),
	(31, 'EXPORT_EMPLOYEE', 'xuất danh sách tất cả nhân viên', 1, '2024-04-29 21:13:30'),
	(32, 'READ_WAREHOUSE_RECEIPT', 'xem danh sách tất cả phiếu nhập', 1, '2024-04-29 21:13:30'),
	(33, 'CREATE_WAREHOUSE_RECEIPT', 'tạo phiếu nhập mới', 1, '2024-04-29 21:13:30'),
	(34, 'UPDATE_WAREHOUSE_RECEIPT', 'chỉnh sửa phiếu nhập', 1, '2024-04-29 21:13:30'),
	(35, 'DELETE_WAREHOUSE_RECEIPT', 'xoá tài khoản phiếu nhập', 1, '2024-04-29 21:13:30'),
	(36, 'IMPORT_WAREHOUSE_RECEIPT', 'tạo phiếu nhập bằng import file excel', 1, '2024-04-29 21:13:30'),
	(37, 'EXPORT_WAREHOUSE_RECEIPT', 'xuất danh sách tất cả phiếu nhập', 1, '2024-04-29 21:13:30'),
	(38, 'READ_WAREHOUSE_DISPATCH_NOTE', 'xem danh sách tất cả phiếu xuất', 1, '2024-04-29 21:13:30'),
	(39, 'CREATE_WAREHOUSE_DISPATCH_NOTE', 'tạo phiếu xuất mới', 1, '2024-04-29 21:13:30'),
	(40, 'UPDATE_WAREHOUSE_DISPATCH_NOTE', 'chỉnh sửa phiếu xuất', 1, '2024-04-29 21:13:30'),
	(41, 'DELETE_WAREHOUSE_DISPATCH_NOTE', 'xoá tài khoản phiếu xuất', 1, '2024-04-29 21:13:30'),
	(42, 'IMPORT_WAREHOUSE_DISPATCH_NOTE', 'tạo phiếu xuất bằng import file excel', 1, '2024-04-29 21:13:30'),
	(43, 'EXPORT_WAREHOUSE_DISPATCH_NOTE', 'xuất danh sách tất cả phiếu xuất', 1, '2024-04-29 21:13:30'),
	(44, 'READ_SUPPLIER', 'xem danh sách tất cả nhà cung cấp', 1, '2024-04-29 21:13:30'),
	(45, 'CREATE_SUPPLIER', 'tạo nhà cung cấp mới', 1, '2024-04-29 21:13:30'),
	(46, 'UPDATE_SUPPLIER', 'chỉnh sửa nhà cung cấp', 1, '2024-04-29 21:13:30'),
	(47, 'DELETE_SUPPLIER', 'xoá tài khoản nhà cung cấp', 1, '2024-04-29 21:13:30'),
	(48, 'IMPORT_SUPPLIER', 'tạo nhà cung cấp bằng import file excel', 1, '2024-04-29 21:13:30'),
	(49, 'EXPORT_SUPPLIER', 'xuất danh sách tất cả nhà cung cấp', 1, '2024-04-29 21:13:30'),
	(50, 'READ_STATISTICAL', 'xem thống kê', 1, '2024-04-29 21:13:30'),
	(51, 'EXPORT_STATISTICAL', 'xuất file excel thống kê', 1, '2024-04-29 21:13:30');

-- Dumping structure for table java_coffee.CongThuc
CREATE TABLE IF NOT EXISTS `CongThuc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_cong_thuc` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `mo_ta` varchar(255) COLLATE utf8mb4_general_ci DEFAULT '',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `is_active` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_cong_thuc` (`ten_cong_thuc`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table java_coffee.CongThuc: ~1 rows (approximately)
INSERT INTO `CongThuc` (`id`, `ten_cong_thuc`, `mo_ta`, `createdAt`, `is_active`) VALUES
	(35, 'ad', 'ádadad', '2024-09-18 14:57:40', 1),
	(36, 'da1', 'ádads', '2024-09-18 15:13:52', 1),
	(37, 'ádasd', 'ccc', '2024-09-18 15:15:17', 1),
	(38, 'ads', 'ádf', '2024-09-18 15:24:24', 1);

-- Dumping structure for table java_coffee.CongThucSanPham
CREATE TABLE IF NOT EXISTS `CongThucSanPham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `SanPham_id` int NOT NULL DEFAULT '0',
  `CongThuc_id` int NOT NULL DEFAULT '0',
  `NguyenLieu_id` int NOT NULL DEFAULT '0',
  `don_vi` varchar(50) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0',
  `so_luong` int NOT NULL DEFAULT '0',
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK_CongThucSanPham_SanPham` (`SanPham_id`),
  KEY `FK_CongThucSanPham_CongThuc` (`CongThuc_id`),
  KEY `FK_CongThucSanPham_NguyenLieu` (`NguyenLieu_id`),
  CONSTRAINT `FK_CongThucSanPham_CongThuc` FOREIGN KEY (`CongThuc_id`) REFERENCES `CongThuc` (`id`),
  CONSTRAINT `FK_CongThucSanPham_NguyenLieu` FOREIGN KEY (`NguyenLieu_id`) REFERENCES `NguyenLieu` (`id`),
  CONSTRAINT `FK_CongThucSanPham_SanPham` FOREIGN KEY (`SanPham_id`) REFERENCES `SanPham` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Dumping data for table java_coffee.CongThucSanPham: ~0 rows (approximately)
INSERT INTO `CongThucSanPham` (`id`, `SanPham_id`, `CongThuc_id`, `NguyenLieu_id`, `don_vi`, `so_luong`, `is_active`, `createdAt`) VALUES
	(1, 1, 38, 112, 'gram', 1, 1, '2024-09-18 15:24:24');

-- Dumping structure for table java_coffee.GioHang
CREATE TABLE IF NOT EXISTS `GioHang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `so_luong` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `SanPham_id` int NOT NULL,
  `TaiKhoan_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_Cart_Products1_idx` (`SanPham_id`),
  KEY `fk_Cart_Account1_idx` (`TaiKhoan_id`),
  CONSTRAINT `fk_Cart_Account1` FOREIGN KEY (`TaiKhoan_id`) REFERENCES `TaiKhoan` (`id`),
  CONSTRAINT `fk_Cart_Products1` FOREIGN KEY (`SanPham_id`) REFERENCES `SanPham` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.GioHang: ~4 rows (approximately)

-- Dumping structure for table java_coffee.HoaDon
CREATE TABLE IF NOT EXISTS `HoaDon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `trang_thai` varchar(45) NOT NULL,
  `PTThanhToan_id` int NOT NULL,
  `TaiKhoan_id` int NOT NULL,
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_Order_ PaymentMethod1_idx` (`PTThanhToan_id`),
  KEY `fk_Order_Account1_idx` (`TaiKhoan_id`),
  CONSTRAINT `fk_Order_ PaymentMethod1` FOREIGN KEY (`PTThanhToan_id`) REFERENCES `PTThanhToan` (`id`),
  CONSTRAINT `fk_Order_Account1` FOREIGN KEY (`TaiKhoan_id`) REFERENCES `TaiKhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.HoaDon: ~47 rows (approximately)

-- Dumping structure for table java_coffee.LoaiSanPham
CREATE TABLE IF NOT EXISTS `LoaiSanPham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_loai` varchar(45) NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.LoaiSanPham: ~3 rows (approximately)
INSERT INTO `LoaiSanPham` (`id`, `ten_loai`, `is_active`, `createdAt`) VALUES
	(1, 'Cà phê', 1, '2024-03-27 23:50:16'),
	(2, 'Trà sữa', 1, '2024-03-27 23:50:16'),
	(3, 'Soda', 1, '2024-03-27 23:50:16');

-- Dumping structure for table java_coffee.NguyenLieu
CREATE TABLE IF NOT EXISTS `NguyenLieu` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_NL` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `don_vi` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL DEFAULT 'KG',
  `so_luong` int NOT NULL,
  `is_active` int DEFAULT '1',
  `created_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=latin1;

-- Dumping data for table java_coffee.NguyenLieu: ~4 rows (approximately)
INSERT INTO `NguyenLieu` (`id`, `ten_NL`, `don_vi`, `so_luong`, `is_active`, `created_date`, `updatedAt`) VALUES
	(109, 'a', 'gram', 0, 0, '2024-09-18 14:34:05', NULL),
	(110, 'a', 'KG', 0, 0, '2024-09-18 14:37:00', NULL),
	(111, 'a', 'gram', 0, 0, '2024-09-18 14:39:33', NULL),
	(112, 'a', 'gram', 0, 1, '2024-09-18 14:39:48', NULL);

-- Dumping structure for table java_coffee.NhaCungCap
CREATE TABLE IF NOT EXISTS `NhaCungCap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_ncc` varchar(100) NOT NULL,
  `dia_chi` varchar(45) NOT NULL,
  `dien_thoai` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `is_active` int DEFAULT '1',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_ncc` (`ten_ncc`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.NhaCungCap: ~24 rows (approximately)

-- Dumping structure for table java_coffee.NhanVien
CREATE TABLE IF NOT EXISTS `NhanVien` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ho_va_ten` varchar(45) NOT NULL,
  `ngay_sinh` date NOT NULL,
  `dia_chi` varchar(254) NOT NULL DEFAULT 'null',
  `chuc_vu` varchar(100) NOT NULL DEFAULT 'null',
  `sdt` varchar(45) NOT NULL DEFAULT 'null',
  `luong` float NOT NULL DEFAULT '0',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `TaiKhoan_id` int NOT NULL,
  `is_active` int DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `TaiKhoan_id` (`TaiKhoan_id`),
  KEY `fk_Employees_Account1_idx` (`TaiKhoan_id`),
  CONSTRAINT `fk_Employees_Account1` FOREIGN KEY (`TaiKhoan_id`) REFERENCES `TaiKhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=234 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.NhanVien: ~36 rows (approximately)
INSERT INTO `NhanVien` (`id`, `ho_va_ten`, `ngay_sinh`, `dia_chi`, `chuc_vu`, `sdt`, `luong`, `createdAt`, `TaiKhoan_id`, `is_active`) VALUES
	(233, 'thuận', '2024-09-18', 'ád', 'Admin', '0945986661', 1, '2024-09-18 14:22:53', 1, 1);

-- Dumping structure for table java_coffee.PhieuNhap
CREATE TABLE IF NOT EXISTS `PhieuNhap` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_phieu_nhap` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TaiKhoan_id` int NOT NULL,
  `NhaCungCap_id` int NOT NULL,
  `is_active` int DEFAULT '1',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `fk_PhieuNhap_TaiKhoan1_idx` (`TaiKhoan_id`),
  KEY `fk_PhieuNhap_NhaCungCap1_idx` (`NhaCungCap_id`),
  CONSTRAINT `fk_PhieuNhap_NhaCungCap1` FOREIGN KEY (`NhaCungCap_id`) REFERENCES `NhaCungCap` (`id`),
  CONSTRAINT `fk_PhieuNhap_TaiKhoan1` FOREIGN KEY (`TaiKhoan_id`) REFERENCES `TaiKhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=latin1;

-- Dumping data for table java_coffee.PhieuNhap: ~37 rows (approximately)

-- Dumping structure for table java_coffee.PhieuXuat
CREATE TABLE IF NOT EXISTS `PhieuXuat` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_phieu_xuat` varchar(45) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `TaiKhoan_id` int NOT NULL,
  `is_active` int DEFAULT '1',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_PhieuXuat_TaiKhoan1_idx` (`TaiKhoan_id`),
  CONSTRAINT `fk_PhieuXuat_TaiKhoan1` FOREIGN KEY (`TaiKhoan_id`) REFERENCES `TaiKhoan` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=latin1;

-- Dumping data for table java_coffee.PhieuXuat: ~24 rows (approximately)

-- Dumping structure for table java_coffee.PTThanhToan
CREATE TABLE IF NOT EXISTS `PTThanhToan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_PT` varchar(45) NOT NULL,
  `mo_ta` varchar(45) NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.PTThanhToan: ~3 rows (approximately)
INSERT INTO `PTThanhToan` (`id`, `ten_PT`, `mo_ta`, `is_active`, `createdAt`) VALUES
	(1, 'Tiền mặt', 'Thanh toán bằng tiền mặt khi nhận hàng', 1, '2024-03-27 23:52:28'),
	(2, 'Thẻ ngân hàng', 'Thanh toán bằng thẻ ngân hàng qua cổng thanh ', 1, '2024-03-27 23:52:28'),
	(3, 'Chuyển khoản', 'Thanh toán bằng chuyển khoản qua ngân hàng', 1, '2024-03-27 23:52:28');

-- Dumping structure for table java_coffee.Quyen
CREATE TABLE IF NOT EXISTS `Quyen` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_quyen` varchar(45) NOT NULL,
  `mo_ta` varchar(45) DEFAULT NULL,
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.Quyen: ~3 rows (approximately)
INSERT INTO `Quyen` (`id`, `ten_quyen`, `mo_ta`, `createdAt`) VALUES
	(1, 'Admin', 'Quyền quản trị cao nhất', '2024-03-27 23:50:16'),
	(2, 'Manager', 'Quyền cấp quản lý', '2024-03-31 23:42:12'),
	(3, 'Staff', 'Quyền cấp nhân viên bán hàng', '2024-03-31 23:42:19');

-- Dumping structure for table java_coffee.QuyenChucNang
CREATE TABLE IF NOT EXISTS `QuyenChucNang` (
  `id` int NOT NULL AUTO_INCREMENT,
  `Quyen_id` int NOT NULL,
  `ChucNang_id` int NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fk_RoleFunction_Functions1_idx` (`ChucNang_id`),
  KEY `fk_RoleFunction_Roles1_idx` (`Quyen_id`),
  CONSTRAINT `fk_RoleFunction_Functions1` FOREIGN KEY (`ChucNang_id`) REFERENCES `ChucNang` (`id`),
  CONSTRAINT `fk_RoleFunction_Roles1` FOREIGN KEY (`Quyen_id`) REFERENCES `Quyen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.QuyenChucNang: ~58 rows (approximately)
INSERT INTO `QuyenChucNang` (`id`, `Quyen_id`, `ChucNang_id`, `is_active`, `createdAt`) VALUES
	(1, 1, 1, 1, '2024-05-03 13:23:28'),
	(2, 2, 2, 1, '2024-05-03 13:23:28'),
	(3, 2, 3, 1, '2024-05-03 13:23:28'),
	(4, 2, 4, 1, '2024-05-03 13:23:28'),
	(5, 2, 6, 1, '2024-05-03 13:23:28'),
	(6, 2, 7, 1, '2024-05-03 13:23:28'),
	(7, 2, 8, 1, '2024-05-03 13:23:28'),
	(8, 2, 9, 1, '2024-05-03 13:23:28'),
	(9, 2, 10, 1, '2024-05-03 13:23:28'),
	(10, 2, 12, 1, '2024-05-03 13:23:28'),
	(11, 2, 13, 1, '2024-05-03 13:23:28'),
	(12, 2, 14, 1, '2024-05-03 13:23:28'),
	(13, 2, 15, 1, '2024-05-03 13:23:28'),
	(14, 2, 16, 1, '2024-05-03 13:23:28'),
	(15, 2, 17, 1, '2024-05-03 13:23:28'),
	(16, 2, 18, 1, '2024-05-03 13:23:28'),
	(17, 2, 19, 1, '2024-05-03 13:23:28'),
	(18, 2, 20, 1, '2024-05-03 13:23:28'),
	(19, 2, 21, 1, '2024-05-03 13:23:28'),
	(20, 2, 22, 1, '2024-05-03 13:23:28'),
	(21, 2, 24, 1, '2024-05-03 13:23:28'),
	(22, 2, 25, 1, '2024-05-03 13:23:28'),
	(23, 2, 26, 1, '2024-05-03 13:23:28'),
	(24, 2, 27, 1, '2024-05-03 13:23:28'),
	(25, 2, 28, 1, '2024-05-03 13:23:28'),
	(26, 2, 30, 1, '2024-05-03 13:23:28'),
	(27, 2, 31, 1, '2024-05-03 13:23:28'),
	(28, 2, 32, 1, '2024-05-03 13:23:28'),
	(29, 2, 33, 1, '2024-05-03 13:23:28'),
	(30, 2, 34, 1, '2024-05-03 13:23:28'),
	(31, 2, 36, 1, '2024-05-03 13:23:28'),
	(32, 2, 37, 1, '2024-05-03 13:23:28'),
	(33, 2, 38, 1, '2024-05-03 13:23:28'),
	(34, 2, 39, 1, '2024-05-03 13:23:28'),
	(35, 2, 40, 1, '2024-05-03 13:23:28'),
	(36, 2, 42, 1, '2024-05-03 13:23:28'),
	(37, 2, 43, 1, '2024-05-03 13:23:28'),
	(38, 2, 44, 1, '2024-05-03 13:23:28'),
	(39, 2, 45, 1, '2024-05-03 13:23:28'),
	(40, 2, 46, 1, '2024-05-03 13:23:28'),
	(41, 2, 48, 1, '2024-05-03 13:23:28'),
	(42, 2, 49, 1, '2024-05-03 13:23:28'),
	(43, 2, 50, 1, '2024-05-03 13:23:28'),
	(44, 2, 51, 1, '2024-05-03 13:23:28'),
	(45, 3, 8, 1, '2024-05-03 13:23:28'),
	(46, 3, 13, 1, '2024-05-03 13:23:28'),
	(47, 3, 14, 1, '2024-05-03 13:23:28'),
	(48, 3, 15, 1, '2024-05-03 13:23:28'),
	(49, 3, 16, 1, '2024-05-03 13:23:28'),
	(50, 3, 19, 1, '2024-05-03 13:23:28'),
	(51, 3, 20, 1, '2024-05-03 13:23:28'),
	(52, 3, 25, 1, '2024-05-03 13:23:28'),
	(53, 3, 32, 1, '2024-05-03 13:23:28'),
	(54, 3, 37, 1, '2024-05-03 13:23:28'),
	(55, 3, 38, 1, '2024-05-03 13:23:28'),
	(56, 3, 43, 1, '2024-05-03 13:23:28'),
	(57, 3, 44, 1, '2024-05-03 13:23:28'),
	(58, 3, 49, 1, '2024-05-03 13:23:28');

-- Dumping structure for table java_coffee.SanPham
CREATE TABLE IF NOT EXISTS `SanPham` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ten_SP` varchar(45) NOT NULL,
  `url_anh` varchar(255) DEFAULT NULL,
  `gia` float NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `so_luong` int NOT NULL DEFAULT '0',
  `createdAt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `LoaiSanPham_id` int NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ten_SP` (`ten_SP`),
  KEY `fk_Products_Categories1_idx` (`LoaiSanPham_id`),
  CONSTRAINT `fk_Products_Categories1` FOREIGN KEY (`LoaiSanPham_id`) REFERENCES `LoaiSanPham` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.SanPham: ~9 rows (approximately)
INSERT INTO `SanPham` (`id`, `ten_SP`, `url_anh`, `gia`, `is_active`, `so_luong`, `createdAt`, `LoaiSanPham_id`, `updatedAt`) VALUES
	(1, 'Cà phê đen', './src/assets/icon/Hình 1.png', 20000, 1, 0, '2024-03-15 22:51:18', 1, '2024-09-14 15:51:41'),
	(2, 'Cà phê sữa', './src/assets/icon/Hình 2.png', 20000, 1, 0, '2024-03-15 22:51:18', 1, '2024-09-08 21:55:50'),
	(3, 'Cà phê thường', './src/assets/icon/Hình 3.png', 20000, 1, 0, '2024-03-15 22:51:18', 1, '2024-03-15 22:51:18'),
	(4, 'Trà sữa đường đen', './src/assets/icon/ts1.png', 25000, 1, 0, '2024-03-15 22:51:18', 2, '2024-03-15 22:51:18'),
	(5, 'Trà sữa match', './src/assets/icon/ts2.png', 25000, 1, 0, '2024-03-15 22:51:18', 2, '2024-04-29 22:24:29'),
	(6, 'Trà sữa khoai môn', './src/assets/icon/ts3.png', 25000, 1, 0, '2024-03-25 14:58:02', 2, '2024-03-15 22:51:18'),
	(7, 'Soda đào', './src/assets/icon/soda4.png', 30000, 1, 0, '2024-03-25 14:59:17', 3, '2024-05-05 23:45:46'),
	(8, 'Soda nho', './src/assets/icon/soda2.png', 30000, 1, 0, '2024-03-25 14:59:24', 3, '2024-03-15 22:51:18'),
	(9, 'Soda dâu', './src/assets/icon/soda3.png', 30000, 1, 0, '2024-03-25 14:59:33', 3, '2024-09-14 15:54:46');

-- Dumping structure for table java_coffee.TaiKhoan
CREATE TABLE IF NOT EXISTS `TaiKhoan` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `is_active` int NOT NULL DEFAULT '1',
  `createdAt` datetime DEFAULT CURRENT_TIMESTAMP,
  `Quyen_id` int NOT NULL,
  `updatedAt` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `fk_Account_Roles_idx` (`Quyen_id`),
  CONSTRAINT `fk_Account_Roles` FOREIGN KEY (`Quyen_id`) REFERENCES `Quyen` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8mb3;

-- Dumping data for table java_coffee.TaiKhoan: ~76 rows (approximately)
INSERT INTO `TaiKhoan` (`id`, `email`, `password`, `is_active`, `createdAt`, `Quyen_id`, `updatedAt`) VALUES
	(1, 'admin@gmail.com', '$2a$10$ylYoqjEd74zJ.Eq0hrUOu.2Z7UDcSfjKc70woelMrzJ7D2LotKKRG', 1, '2024-03-27 23:50:16', 1, '2024-04-29 23:12:40'),
	(39, 'client@gmail.com', '$2a$10$eaejtlT893DbKeLgEUYKme61rcmP9UAOhmNkNTQ16iiuOLlpvtgEm', 1, '2024-04-29 21:27:11', 3, NULL),
	(40, 'manager@gmail.com', '$2a$10$chDhlCBYstLSHmcdM7vI8OSJfsRHkfQjSja1Rf0lNxZFUjxPynytC', 1, '2024-04-29 21:28:52', 2, '2024-04-29 23:13:04');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
