# Security Policy

## Supported Versions

| Version | Supported          |
| ------- | ------------------ |
| 1.x.x   | :white_check_mark: |
| < 1.0   | :x:                |

## Reporting a Vulnerability

We take security vulnerabilities seriously. If you discover a security vulnerability in our SDK, please follow these steps:

### **IMPORTANT: Do NOT create a public GitHub issue for security vulnerabilities**

1. **Email us directly** at [volodymyr.nevmerzhytskyi@sdkforge.dev](mailto:volodymyr.nevmerzhytskyi@sdkforge.dev)
2. **Include detailed information** about the vulnerability:
   - Description of the issue
   - Steps to reproduce
   - Potential impact
   - Suggested fix (if any)
   - Platform affected (Android/iOS/both)

### What to Include in Your Report

- **Vulnerability Type**: (e.g., buffer overflow, injection, authentication bypass)
- **Affected Components**: Which part of the SDK is affected
- **Platform Impact**: Android, iOS, or both platforms
- **Severity Assessment**: Critical, High, Medium, or Low
- **Proof of Concept**: If possible, provide a minimal reproduction case
- **Suggested Mitigation**: Any ideas for fixing the issue

## Response Timeline

- **Initial Response**: Within 48 hours
- **Status Update**: Within 7 days
- **Resolution**: Depends on complexity and severity

## Disclosure Policy

- Patches will be released as soon as possible
- Credit will be given to reporters unless they prefer to remain anonymous

## Security Best Practices

When using our SDKs, please:

### General Security
- Keep your SDKs version updated
- Follow platform-specific security guidelines
- Implement proper authentication and authorization
- Validate all inputs
- Use HTTPS for network communications

### Android Security
- Follow Android security best practices
- Use ProGuard/R8 for code obfuscation
- Implement certificate pinning if needed
- Use Android Keystore for sensitive data

### iOS Security
- Follow iOS security best practices
- Use Keychain for sensitive data storage
- Implement App Transport Security (ATS)
- Use code signing and provisioning profiles

### Kotlin Multiplatform Security
- Validate shared code across platforms
- Test security features on all target platforms
- Ensure consistent security behavior across platforms
- Review platform-specific implementations

## Security Features

Our SDKs includes several security features:

- **Input Validation**: Comprehensive input sanitization
- **Secure Communication**: TLS/SSL support
- **Data Protection**: Encryption for sensitive data
- **Platform Integration**: Native security APIs integration

## Security Updates

- Security patches are released as patch versions (e.g., 1.0.1)
- Release notes include security-related changes

## Contact Information

For security-related questions or concerns:

- **Security Email**: [volodymyr.nevmerzhytskyi@sdkforge.dev](mailto:volodymyr.nevmerzhytskyi@sdkforge.dev)
- **Response Time**: Within 48 hours for initial response

## Acknowledgments

We appreciate security researchers and community members who help us maintain the security of our SDKs. Contributors will be acknowledged in our security advisories unless they prefer to remain anonymous.
