package TableTap.security.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EmailTemplates {
    public static String getReservationConfirmation(String email,
                                                    String restaurantName,
                                                    LocalDateTime reservationTime,
                                                    LocalDateTime validUntil,
                                                    int numOfGuests) {
        return """
        <!DOCTYPE html>
        <html>
        <head>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    line-height: 1.6;
                    color: #333333;
                    max-width: 600px;
                    margin: 0 auto;
                    padding: 20px;
                }
                .container {
                    background-color: #ffffff;
                    border-radius: 8px;
                    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                    padding: 30px;
                }
                .header {
                    text-align: center;
                    padding-bottom: 20px;
                    border-bottom: 2px solid #f0f0f0;
                    margin-bottom: 20px;
                }
                .header h1 {
                    color: #2c3e50;
                    margin: 0;
                    font-size: 24px;
                }
                .logo-container {
                    width: 120px;
                    height: 120px;
                    margin: 0 auto 20px;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
                .logo-image {
                    width: 100%%;
                    height: 100%%;
                    object-fit: contain;
                }
                .content {
                    padding: 20px 0;
                }
                .info-box {
                    background: linear-gradient(to right, #f8f9fa, #ffffff);
                    border-left: 4px solid #2ecc71;
                    border-radius: 6px;
                    padding: 20px;
                    margin: 15px 0;
                    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.05);
                }
                .footer {
                    text-align: center;
                    padding-top: 20px;
                    border-top: 2px solid #f0f0f0;
                    margin-top: 20px;
                    font-size: 14px;
                    color: #666666;
                }
                .guest-info {
                    text-align: center;
                    margin: 15px 0;
                    font-size: 18px;
                    color: #2c3e50;
                }
                .guest-icon {
                    font-size: 24px;
                    margin-right: 5px;
                }
            </style>
        </head>
        <body>
            <div class="container">
                <div class="header">
                    <div class="logo-container">
                        <img class="logo-image" src="cid:menu-logo" alt="TableTap Logo">
                    </div>
                    <h1>Reservation Confirmed!</h1>
                    <div class="guest-info">
                        <span class="guest-icon">👥</span>
                        <span>Group Size: %d guests</span>
                    </div>
                </div>
                
                <div class="content">
                    <p>Your reservation has been successfully confirmed.</p>
                    
                    <div class="info-box">
                        <p><strong>Email:</strong> %s</p>
                        <p><strong>Restaurant:</strong> %s</p>
                        <p><strong>Date & Time:</strong> %s</p>
                        <p><strong>Valid Until:</strong> %s</p>
                    </div>
                    
                    <p>Please arrive on time. Your reservation will be held for up to 15 minutes after the scheduled time.</p>
                </div>
                
                <div class="footer">
                    <p>Thank you for choosing TableTap!</p>
                    <p>If you need to modify or cancel your reservation, please contact the restaurant directly.</p>
                </div>
            </div>
        </body>
        </html>
        """.formatted(
                numOfGuests,
                email,
                restaurantName,
                reservationTime.format(DateTimeFormatter.ofPattern("MMMM d, yyyy 'at' h:mm a")),
                validUntil.format(DateTimeFormatter.ofPattern("h:mm a"))
        );
    }
}

